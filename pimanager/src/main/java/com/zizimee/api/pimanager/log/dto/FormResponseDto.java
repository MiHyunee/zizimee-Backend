package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FormResponseDto {
    private Long id;
    private Enterprise enterpriseId;
    private String consentItem;

    public FormResponseDto(ConsentForm entity){
        this.id = entity.getId();
        this.enterpriseId = entity.getEnterpriseId();
        this.consentItem = entity.getConsentItem();
    }

    @Builder
    public FormResponseDto(Long id){
        this.id = id;
    }
}
