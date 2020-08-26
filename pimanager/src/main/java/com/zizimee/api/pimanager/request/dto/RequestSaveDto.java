package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.RequestType;
import lombok.Builder;

import java.time.LocalDate;

public class RequestSaveDto {
    private RequestType type;
    private String content;
    private LocalDate requestDate;
    private Enterprise enterpriseId;

    @Builder
    public RequestSaveDto(RequestType type, String content, LocalDate requestDate, Enterprise enterpriseId){
        this.type = type;
        this.content = content;
        this.requestDate = requestDate;
        this.enterpriseId = enterpriseId;
    }

    public Request toEntity(){
        return Request.builder()
                .type(type)
                .content(content)
                .requestDate(requestDate)
                .enterpriseId(enterpriseId)
                .build();
    }
}
