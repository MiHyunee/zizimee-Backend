package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.entity.ConsentForm;
import lombok.Builder;

public class FormSaveDto {
    private Enterprise enterpriseId;

    @Builder
    public FormSaveDto(Enterprise enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
    public ConsentForm toEntity(Enterprise enterpriseId) {
        return ConsentForm.builder()
                .enterpriseId(enterpriseId)
                .build();
    }
}