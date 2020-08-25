package com.zizimee.api.pimanager.enterprise.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestSignInDto {

    private String signUpId;
    private String password;

    @Builder
    public RequestSignInDto(String signUpId, String password) {
        this.signUpId = signUpId;
        this.password = password;
    }

}
