package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.request.entity.RequestType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestUpdateDto {
    private RequestType type;
    private String content;
    private LocalDate requestDate;
    private String name;

    @Builder
    public RequestUpdateDto(RequestType type, String content, LocalDate requestDate, String name){
        this.type = type;
        this.content = content;
        this.requestDate = requestDate;
        this.name = name;
    }
}
