package com.zizimee.api.pimanager.report.service;

import com.zizimee.api.pimanager.report.dto.ReportResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.entity.Report;
import com.zizimee.api.pimanager.report.entity.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public Long save(ReportSaveRequestDto requestDto){

        return reportRepository.save(requestDto.toEntity()).getId_report();
    }

    @Transactional
    public void delete(Long id_report){
        Report report = reportRepository.findById(id_report)
                .orElseThrow(()-> new IllegalArgumentException("해당 레포트가 없습니다."));
        reportRepository.delete(report);
    }

    @Transactional(readOnly=true)
    public ReportResponseDto findById(Long id_report) {
        Report entity = reportRepository.findById(id_report)
                .orElseThrow(()-> new IllegalArgumentException("해당 레포트가 없습니다."));
        return new ReportResponseDto(entity);
    }

}
