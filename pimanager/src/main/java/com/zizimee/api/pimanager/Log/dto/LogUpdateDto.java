package com.zizimee.api.pimanager.Log.dto;

import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LogUpdateDto {
    private String name;
    private String intend;
    private String providedInfo;
    private String thirdParty;
    private LocalDate useDate;

    @Builder
    public LogUpdateDto(String intend, String providedInfo, String thirdParty, LocalDate useDate, String name){
        this.intend = intend;
        this.providedInfo = providedInfo;
        this.thirdParty = thirdParty;
        this.useDate = useDate;
        this.name = name;
        }
}
