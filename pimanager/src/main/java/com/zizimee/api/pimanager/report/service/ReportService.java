package com.zizimee.api.pimanager.report.service;

import com.zizimee.api.pimanager.report.dto.ReportListResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.entity.Report;
import com.zizimee.api.pimanager.report.entity.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public Long save(ReportSaveRequestDto requestDto){

        return reportRepository.save(requestDto.toEntity()).getIdReport();
    }

    @Transactional
    public void delete(Long idReport){
        Report report = reportRepository.findById(idReport)
                .orElseThrow(()-> new IllegalArgumentException("해당 레포트가 없습니다."));
        reportRepository.delete(report);
    }

    @Transactional(readOnly=true)
    public ReportResponseDto findById(Long idReport) {
        Report entity = reportRepository.findById(idReport)
                .orElseThrow(()-> new IllegalArgumentException("해당 레포트가 없습니다."));
        return new ReportResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ReportListResponseDto> findAllDesc() {
        return reportRepository.findAllDesc().stream()
                .map(ReportListResponseDto::new)
                .collect(Collectors.toList());
    }

}
