package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import com.zizimee.api.pimanager.consentManagement.entity.EntLinkage;
import lombok.Builder;

public class StatusResponseDto {
    private Long id;
    private ConsentForm formId;
    private EntLinkage linkageId;
    private boolean isConsent;
    private Long signId;
    private byte[] signature;

    public void StatusResponseDto(ConsentStatus entity){
        this.id = entity.getId();
        this.formId = entity.getFormId();
        this.linkageId = entity.getLinkId();
        this.isConsent = entity.getIsConsent();
        this.signId = entity.getSignId();
        this.signature = entity.getSignature();
    }

    @Builder
    public StatusResponseDto(Long id){
        this.id = id;
    }
}
