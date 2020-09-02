package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.log.entity.Log;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LogResponseDto {
    private Long id;
    private User userId;
    private Enterprise enterpriseId;
    private String intend;
    private String providedInfo;
    private String thirdParty;
    private LocalDate useDate;

    public LogResponseDto(Log entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.enterpriseId = entity.getEnterpriseId();
        this.intend = entity.getIntend();
        this.providedInfo = entity.getProvidedInfo();
        this.thirdParty = entity.getThirdParty();
        this.useDate = entity.getUseDate();
    }

    @Builder
    public LogResponseDto(Long id){
        this.id = id;
    }
}
