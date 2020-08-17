package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.Enterprise.Entity.Enterprise;
import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReportResponseDto {
    private Long idReport;
    private Enterprise idEnterprise;
    private Date startDate;
    private Date endDate;
    private String imageUrl;

    public ReportResponseDto(Report entity){
        this.idReport = getIdReport();
        this.idEnterprise = getIdEnterprise();
        this.startDate = getStartDate();
        this.endDate = getEndDate();
        this.imageUrl = getImageUrl();
    }
}
