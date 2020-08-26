package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.RequestType;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestSaveDto {

    private RequestType type;
    private String content;
    private LocalDate requestDate;
    private String name;

    @Builder
    public RequestSaveDto(RequestType type, String content, LocalDate requestDate, String name){
        this.type = type;
        this.content = content;
        this.requestDate = requestDate;
        this.name = name;
    }

    public Request toEntity(Enterprise enterpriseId, User userId){
        return Request.builder()
                .type(type)
                .content(content)
                .requestDate(requestDate)
                .enterpriseId(enterpriseId)
                .userId(userId)
                .build();
    }

}
