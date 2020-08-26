package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.Request.Type;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestUpdateDto {
    private Type type;
    private String content;
    private LocalDate requestDate;

    @Builder
    public RequestUpdateDto(Type type, String content, LocalDate requestDate){
        this.type = type;
        this.content = content;
        this.requestDate = requestDate;
    }
}
