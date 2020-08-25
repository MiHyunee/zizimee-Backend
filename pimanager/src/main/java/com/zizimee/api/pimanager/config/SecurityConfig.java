package com.zizimee.api.pimanager.config;

import com.zizimee.api.pimanager.common.jwt.JwtAuthenticationFilter;
import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/user/sign-in", "/user/login",
                "/enterprise/sign-up", "/enterprise/sign-in", "/enterprise/idInquiry", "/enterprise/pwInquiry",
                "/h2-console/**", "/swagger-resources/**", "/swagger-ui.html",
                "/v2/api-docs" , "/configuration/ui" , "/configuration/security");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                    .disable()           //개발 편의성 위해 csrf 토큰 검사 비활성화
                .headers().frameOptions().disable()
                .and()
                .httpBasic()
                    .disable()  //basic authentication 비활성화
                .authorizeRequests() //URL별 권한 관리 설정 옵션 시작점(인가에 관련한 처리)
                    .anyRequest()  //설정 값(antMatchers) 이외 나머지 URL
                        .permitAll()  //인증된 사용자(로그인 한 사용자)들만 허용
        ;

        //security는 id-pw기반인증
        //username = id
        //session disable
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
