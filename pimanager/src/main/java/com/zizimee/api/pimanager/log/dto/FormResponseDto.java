package com.zizimee.api.pimanager.log.dto;

import lombok.Builder;
import lombok.Getter;

import java.security.PublicKey;

@Getter
public class FormResponseDto {
    private Long id;
    private PublicKey publicKey;

    public FormResponseDto(Long id, PublicKey publicKey){
        this.id = id;
        this.publicKey = publicKey;
    }

    @Builder
    public FormResponseDto(Long id){
        this.id = id;
    }
}
