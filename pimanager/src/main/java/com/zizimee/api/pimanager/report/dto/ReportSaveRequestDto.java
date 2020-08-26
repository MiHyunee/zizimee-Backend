package com.zizimee.api.pimanager.report.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.report.entity.Report;
import jdk.vm.ci.meta.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

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
