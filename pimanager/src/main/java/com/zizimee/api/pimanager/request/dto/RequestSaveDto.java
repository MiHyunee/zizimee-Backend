package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.Request.Type;
import lombok.Builder;

import java.time.LocalDate;

public class RequestSaveDto {
    private Type type;
    private String content;
    private LocalDate requestDate;

    @Builder
    public RequestSaveDto(Type type, String content, LocalDate requestDate){
        this.type = type;
        this.content = content;
        this.requestDate = requestDate;
    }

    public Request toEntity(){
        return Request.builder()
                .type(type)
                .content(content)
                .requestDate(requestDate)
                .build();
    }
}
