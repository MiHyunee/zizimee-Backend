package com.zizimee.api.pimanager.request.dto;

import lombok.Getter;
import lombok.Builder;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
public class RequestResponseDto {

    String entName;
    LocalDate startDate;
    LocalDate endDate;
    HashMap<String, Character[]> form;

    @Builder
    public RequestResponseDto(String entName, LocalDate startDate, LocalDate endDate, HashMap<String, Character[]> form) {
        this.entName = entName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.form = form;
    }
}
