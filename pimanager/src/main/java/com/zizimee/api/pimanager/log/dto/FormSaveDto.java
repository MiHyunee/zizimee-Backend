package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.entity.ConsentForm;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FormSaveDto {

    public ConsentForm toEntity(String consentItem, Enterprise enterpriseId){
        return ConsentForm.builder()
                .consentItem(consentItem)
                .enterpriseId(enterpriseId)
                .build();
    }
}
