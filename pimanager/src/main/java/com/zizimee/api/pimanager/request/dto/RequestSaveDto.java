package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestSaveDto {

    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private Enterprise enterpriseId;

    @Builder
    public RequestSaveDto( String content, LocalDate startDate, LocalDate endDate, Enterprise enterpriseId){
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enterpriseId = enterpriseId;
    }

    public Request toEntity(User userId){
        return Request.builder()
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .enterpriseId(enterpriseId)
                .userId(userId)
                .build();
    }


}
