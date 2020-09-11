package com.zizimee.api.pimanager.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSignUpDto {

    //오프라인일 경우 사용자 대신 google api 부르기 바란다면 -> access token도 서버로 받아오기

    private ProviderType provider;
    private String idToken;

}
