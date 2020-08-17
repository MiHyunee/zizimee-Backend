package com.zizimee.api.pimanager.user.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.user.dto.RequestSignUpDto;
import com.zizimee.api.pimanager.user.dto.ResponseSignUpDto;
import com.zizimee.api.pimanager.user.entity.User;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpTransport httpTransport = new NetHttpTransport();
    private final JsonFactory jsonFactory = new JacksonFactory();
    private final String CLIENT_ID = "앱 클라이언트 아이디 만들기";


    public ResponseEntity<ResponseSignUpDto> verifyIdToken(RequestSignUpDto requestDto) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singleton(CLIENT_ID))
                .build();

        //idToken 무결성 확인
        GoogleIdToken idToken = verifier.verify(requestDto.getIdToken());

        if(idToken != null) {
            Payload payload = idToken.getPayload();

            String convertedUid = getConvertedUidFromUid(requestDto.getProvider().toString(),
                    payload.getSubject());

            Optional<User> optionalUser = userRepository.findByUid(convertedUid);

            if(optionalUser.isPresent())
                return signIn(convertedUid);
            else {
                return signUp(User.builder()
                        .uid(convertedUid)
                        .name((String) payload.get("name"))
                        .profileImg((String) payload.get("picture"))
                        .provider(requestDto.getProvider())
                        .build());
            }
        } else {
            throw new Exception("INVALID_IDTOKEN");
        }
    }

    private ResponseEntity<ResponseSignUpDto> signIn(String uid) {
        User user = userRepository.findByUid(uid).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원"));

        String jwt = jwtTokenProvider.createToken(uid);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseSignUpDto.builder()
                        .name(user.getName())
                        .profileImg(user.getProfileImg())
                        .jwt(jwt)
                        .build());
    }

    private ResponseEntity<ResponseSignUpDto> signUp(User loginUser) {

        userRepository.save(loginUser);

        String jwt = jwtTokenProvider.createToken(loginUser.getUid());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseSignUpDto.builder()
                        .name(loginUser.getName())
                        .profileImg(loginUser.getProfileImg())
                        .jwt(jwt)
                        .build());
    }

    private boolean isExisted(String uid) {
        return userRepository.findByUid(uid).isPresent();
    }

    private String getConvertedUidFromUid(String provider, String uid) {
        StringBuilder convertedUid = new StringBuilder();
        convertedUid.append(provider);
        convertedUid.append("_");
        convertedUid.append(uid);

        return convertedUid.toString();
    }

    public ResponseEntity<ResponseSignUpDto> loginByToken(String token) throws Exception {
        if(jwtTokenProvider.validateToken(token)) {
            User user = userRepository.getOne((long)Integer.parseInt(jwtTokenProvider.getUserId(token)));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseSignUpDto.builder()
                    .name(user.getName())
                    .build());
        } else {
            throw new Exception("INVALID_TOKEN");
        }
    }
}
