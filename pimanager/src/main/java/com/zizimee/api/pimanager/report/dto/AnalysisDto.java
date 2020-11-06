package com.zizimee.api.pimanager.report.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class AnalysisDto {
    public Long deleteCnt;
    public Map<String, Integer> wordList;

    @Builder
    public AnalysisDto(Long deleteCnt, Map<String, Integer> wordList) {
        this.deleteCnt = deleteCnt;
        this.wordList = wordList;
    }
}
