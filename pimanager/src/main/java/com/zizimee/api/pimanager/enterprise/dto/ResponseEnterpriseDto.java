package com.zizimee.api.pimanager.enterprise.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseEnterpriseDto {

    private Long id;
    private String name;
    private String token;
    private String signUpId;


    @Builder
    public ResponseEnterpriseDto(Long id, String name, String token, String signUpId) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.signUpId = signUpId;
    }

}
