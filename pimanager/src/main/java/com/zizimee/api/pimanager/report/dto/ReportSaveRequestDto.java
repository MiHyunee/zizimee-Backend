package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private String imageUrl;

    @Builder
    public ReportSaveRequestDto(LocalDate startDate, LocalDate endDate, String imageUrl){
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
