package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReportResponseDto {
    private Long id_report;
    private int id_enterprise;
    private Date start_date;
    private Date end_date;
    private String image_url;

    public ReportResponseDto(Report entity){
        this.id_report = getId_report();
        this.id_enterprise = getId_enterprise();
        this.start_date = getStart_date();
        this.end_date = getEnd_date();
        this.image_url = getImage_url();
    }
}
