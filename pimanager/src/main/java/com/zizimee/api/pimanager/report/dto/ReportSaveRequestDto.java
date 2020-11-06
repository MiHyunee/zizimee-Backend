package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.report.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {

    private Long idEnterprise;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public ReportSaveRequestDto(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Report toEntity(Enterprise enterprise){
        return Report.builder()
                .enterprise(enterprise)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
