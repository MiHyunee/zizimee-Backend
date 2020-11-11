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
    private Long enterpriseId;

    public Request toEntity(User userId, Enterprise enterprise){
        return Request.builder()
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .enterpriseId(enterprise)
                .userId(userId)
                .build();
    }


}
