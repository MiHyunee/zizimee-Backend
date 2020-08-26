package com.zizimee.api.pimanager.Log.dto;

import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LogUpdateDto {
    private User userId;
    private String intend;
    private String providedInfo;
    private String thirdParty;
    private LocalDate useDate;

    @Builder
    public LogUpdateDto(String intend, String providedInfo, String thirdParty, LocalDate useDate, User userId){
        this.intend = intend;
        this.providedInfo = providedInfo;
        this.thirdParty = thirdParty;
        this.useDate = useDate;
        this.userId = userId;
        }
}
