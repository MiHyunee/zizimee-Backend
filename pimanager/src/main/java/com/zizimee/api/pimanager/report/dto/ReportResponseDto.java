package com.zizimee.api.pimanager.report.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;


@Getter
public class ReportResponseDto {
    private String enterpriseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long deleteCnt;
    private Map<String, Integer> wordList;

    @Builder
    public ReportResponseDto(String enterpriseName, Long deleteCnt, Map<String, Integer> wordList, LocalDate startDate, LocalDate endDate){
        this.enterpriseName = enterpriseName;
        this.deleteCnt = deleteCnt;
        this.wordList = wordList;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
