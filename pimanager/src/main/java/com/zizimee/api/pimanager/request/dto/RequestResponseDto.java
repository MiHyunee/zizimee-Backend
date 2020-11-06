package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Getter;
import lombok.Builder;

import java.time.LocalDate;

@Getter
public class RequestResponseDto {
    private Long id;
    private User userId;
    private Enterprise enterpriseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String content;
    private LocalDate updateDate;

    public RequestResponseDto(Request entity){
        this.id = entity.getId();
        this.userId  = entity.getUserId();
        this.enterpriseId = entity.getEnterpriseId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.content = entity.getContent();
        this.updateDate = entity.getUpdateDate();
    }

    @Builder
    public RequestResponseDto(Long id){
        this.id = id;
    }
}
