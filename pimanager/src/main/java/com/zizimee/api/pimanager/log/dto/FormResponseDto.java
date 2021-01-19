package com.zizimee.api.pimanager.log.dto;

import lombok.Getter;

@Getter
public class FormResponseDto {
    private byte[] publicKey;

    public FormResponseDto(byte[] bytePublic){
        this.publicKey = bytePublic;
    }
}
