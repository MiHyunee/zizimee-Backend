package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ReportListResponseDto {
    private Long idReport;
    private String imageUrl;
    private Date startDate;
    private Date endDate;
    private LocalDateTime createdDate;

    public ReportListResponseDto(Report entity) {
        this.idReport = entity.getIdReport();
        this.imageUrl = entity.getImageUrl();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }
}
