package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {
    private Date startDate;
    private Date endDate;
    private String imageUrl;

    @Builder
    public ReportSaveRequestDto(Date startDate, Date endDate, String imageUrl){
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    public Report toEntity(){
        return Report.builder()
                .startDate(startDate)
                .endDate(endDate)
                .imageUrl(imageUrl)
                .build();
    }

}
