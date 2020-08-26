package com.zizimee.api.pimanager.Log.dto;

import com.zizimee.api.pimanager.Log.entity.Log;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LogSaveDto {
    private User userId;
    private String intend;
    private String providedInfo;
    private String thirdParty;
    private LocalDate useDate;

    @Builder
    public LogSaveDto(User userId, String intend, String providedInfo, String thirdParty, LocalDate useDate){
        this.userId = userId;
        this.intend = intend;
        this.providedInfo = providedInfo;
        this.thirdParty = thirdParty;
        this.useDate = useDate;
    }

    public Log toEntity(){
        return Log.builder()
                .userId(userId)
                .intend(intend)
                .providedInfo(providedInfo)
                .thirdParty(thirdParty)
                .useDate(useDate)
                .build();
    }
}
