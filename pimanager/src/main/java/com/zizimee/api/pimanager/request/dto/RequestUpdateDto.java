package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.RequestType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestUpdateDto {
    private RequestType type;
    private String content;
    private LocalDate requestDate;

    @Builder
    public RequestUpdateDto(RequestType type, String content, LocalDate requestDate){
        this.type = type;
        this.content = content;
        this.requestDate = requestDate;
    }
}