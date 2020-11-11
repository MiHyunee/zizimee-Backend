package com.zizimee.api.pimanager.log.dto;

import lombok.Builder;
import lombok.Getter;

import java.security.PublicKey;


@Getter
public class SignDto {
    private Long signId;
    private PublicKey pub;

    @Builder
    public SignDto(Long signId, PublicKey pub){
        this.signId = signId;
        this.pub = pub;
    }

}
