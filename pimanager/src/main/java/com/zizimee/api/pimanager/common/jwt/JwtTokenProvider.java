package com.zizimee.api.pimanager.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //암호화 키 : 풀기 힘든 값으로 지정
    private String secretKey = "Zizimee";

    //토큰 유효시간 30일
    private long tokenValidTime = 30 * 24 * 60 * 60 * 1000L;

    public static final String HEADER_NAME = "Authorization";

    @Qualifier("EnterpriseService")
    private UserDetailsService userDetailsService;


    //객체 초기화
    //secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {

        secretKey = Base64.getEncoder()
                .encodeToString(secretKey.getBytes());
    }

    //라이브러리로 jwt 토큰 생성
    public String createToken(String userId) {
        /*
        *Claim == jwt의 속성정보의 한 조각 (name-value쌍)
        *jwt구조 = header.payload.signature
        *header = 토큰의 유형(jwt) + 해시 암호화 알고리즘
        *payload = 토큰에 담을 클레임 정보
         */
        Claims claims = Jwts.claims().setSubject(userId);
        Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)  //토큰 발행 시간 정보
                .setExpiration(new Date(date.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)   //서명
                .compact();

    }

    //토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    //토큰에서 회원정보 parsing
    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Header에서 토큰 값 가져오기 (HEADER_NAME : "TOKEN값")
    public String getTokenFromHeader(HttpServletRequest request) { return request.getHeader(HEADER_NAME); }

    //토큰 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
