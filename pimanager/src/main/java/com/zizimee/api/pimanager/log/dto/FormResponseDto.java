package com.zizimee.api.pimanager.log.dto;

import lombok.Builder;
import lombok.Getter;

import java.security.PublicKey;

@Getter
public class FormResponseDto {
    private PublicKey publicKey;

    public FormResponseDto(PublicKey publicKey){
        this.publicKey = publicKey;
    }
}
