package com.zizimee.api.pimanager.log.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ConsentStatusRequestDto {
    private byte[] isConsent;
    private String uid;
    private Long signId;
    private byte[] signature;

    @Builder
    public ConsentStatusRequestDto(byte[] isConsent, String uid, Long signId, byte[] signature){
        this.isConsent = isConsent;
        this.uid = uid;
        this.signature = signature;
        this.signId = signId;
    }
}
