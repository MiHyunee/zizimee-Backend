package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {
    private Date start_date;
    private Date end_date;
    private String image_url;

    @Builder
    public ReportSaveRequestDto(Date start_end, Date end_date, String image_url){
        this.start_date = start_end;
        this.end_date = end_date;
        this.image_url = image_url;
    }

    public Report toEntity(){
        return Report.builder()
                .start_date(start_date)
                .end_date(end_date)
                .image_url(image_url)
                .build();
    }

}
