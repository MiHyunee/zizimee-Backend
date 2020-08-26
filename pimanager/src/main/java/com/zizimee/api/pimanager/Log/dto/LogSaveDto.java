package com.zizimee.api.pimanager.Log.dto;

import com.zizimee.api.pimanager.Log.entity.Log;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LogSaveDto {
    private String intend;
    private String providedInfo;
    private String thirdParty;
    private LocalDate useDate;
    private String name;

    @Builder
    public LogSaveDto(String intend, String providedInfo, String thirdParty, LocalDate useDate, String name){
        this.intend = intend;
        this.providedInfo = providedInfo;
        this.thirdParty = thirdParty;
        this.useDate = useDate;
        this.name = name;
    }

    public Log toEntity(User userId){
        return Log.builder()
                .userId(userId)
                .intend(intend)
                .providedInfo(providedInfo)
                .thirdParty(thirdParty)
                .useDate(useDate)
                .build();
    }
}
