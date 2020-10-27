package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import lombok.Builder;

public class StatusSaveDto {
    private String isConsent;
    private Long signId;
    private byte[] signature;

    @Builder
    public StatusSaveDto(String isConsent, Long signId, byte[] signature){
        this.isConsent = isConsent;
        this.signId = signId;
        this.signature = signature;
    }

    public ConsentStatus toEntity(){
        return ConsentStatus.builder()
                .isConsent(isConsent)
                .signId(signId)
                .signature(signature)
                .build();
    }
}
