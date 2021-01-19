package com.zizimee.api.pimanager.enterprise.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class RequestSignUpDto {

    private String name;
    private String signUpId;
    private String password;
    private String email;
    private String domainAddress;
    private String registerNmb;

    private String fcmToken;

    public Enterprise toEntity() {
        return Enterprise.builder()
                .name(name)
                .signUpId(signUpId)
                .email(email)
                .domainAddress(domainAddress)
                .registerNmb(registerNmb)
                .fcmToken(fcmToken)
                .build();
    }


}
