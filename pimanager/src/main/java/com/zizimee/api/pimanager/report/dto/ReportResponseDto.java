package com.zizimee.api.pimanager.report.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
public class ReportResponseDto {
    private int agree;
    private int disagree;

    @Builder
    public ReportResponseDto(int agree, int disagree){
        this.agree = agree;
        this.disagree = disagree;
    }
}