package com.zizimee.api.pimanager.log.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FormUpdateDto {

    private String consentItem;

    @Builder
    public FormUpdateDto(String consentItem){
        this.consentItem = consentItem;
    }



}
