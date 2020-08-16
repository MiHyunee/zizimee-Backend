package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ReportListResponseDto {
    private Long id_report;
    private String image_url;
    private Date start_date;
    private Date end_date;
    private LocalDateTime create_date;

    public ReportListResponseDto(Report entity) {
        this.id_report = entity.getId_report();
        this.image_url = entity.getImage_url();
        this.start_date = entity.getStart_date();
        this.end_date = entity.getEnd_date();
    }
}
