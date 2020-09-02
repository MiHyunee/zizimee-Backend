package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.RequestType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestUpdateDto {
    private RequestType type;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;

    @Builder
    public RequestUpdateDto(RequestType type, String content, LocalDate startDate, LocalDate endDate, String name){
        this.type = type;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }
}
