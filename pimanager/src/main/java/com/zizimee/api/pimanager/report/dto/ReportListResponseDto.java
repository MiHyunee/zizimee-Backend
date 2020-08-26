package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ReportListResponseDto {
    private Long idReport;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdDate;

    public ReportListResponseDto(Report entity) {
        this.idReport = entity.getId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.createdDate = entity.getCreatedDate();
    }
}
