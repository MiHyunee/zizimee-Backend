package com.zizimee.api.pimanager.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSignUpDto {

    private String name;
    private String jwt;
    private String profileImg;

    @Builder
    public ResponseSignUpDto(String jwt, String name, String profileImg) {
        this.name = name;
        this.jwt = jwt;
        this.profileImg = profileImg;
    }
}
