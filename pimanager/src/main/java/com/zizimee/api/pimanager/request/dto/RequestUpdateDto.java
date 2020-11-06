package com.zizimee.api.pimanager.request.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestUpdateDto {
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;

    @Builder
    public RequestUpdateDto(String content, LocalDate startDate, LocalDate endDate, String name){
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }

}
