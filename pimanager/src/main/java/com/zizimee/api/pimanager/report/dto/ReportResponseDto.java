package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.Enterprise.Entity.Enterprise;
import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReportResponseDto {
    private Long id;
    private Enterprise enterpriseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imageUrl;
    private String reportDate;

    public ReportResponseDto(Report entity){
        this.id = entity.getId();
        this.enterpriseId = entity.getEnterpriseId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.imageUrl = entity.getImageUrl();
        this.reportDate = entity.getReportDate().toString();
    }

    @Builder
    public void ReportResponseDto(Long id){
        this.id = id;
    }
}
