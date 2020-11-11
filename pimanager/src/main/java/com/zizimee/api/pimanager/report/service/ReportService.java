package com.zizimee.api.pimanager.report.service;

import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.report.dto.AnalysisDto;
import com.zizimee.api.pimanager.report.dto.ReportListResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.entity.Report;
import com.zizimee.api.pimanager.report.entity.ReportRepository;
import com.zizimee.api.pimanager.request.entity.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final RequestRepository requestRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final Analysis analysis;

    @Transactional
    public AnalysisDto save(ReportSaveRequestDto requestDto){
        reportRepository.save(requestDto.toEntity(enterpriseRepository.getOne(requestDto.getIdEnterprise())));
        Long deleteCnt = requestRepository.countByEnterpriseIdAndStartDateBetween(enterpriseRepository.getOne(requestDto.getIdEnterprise()), requestDto.getStartDate(), requestDto.getEndDate());
        List<String> contentList = requestRepository.getContents(requestDto.getIdEnterprise(), requestDto.getStartDate(), requestDto.getEndDate())
                .stream().map(String::toString).collect(Collectors.toList());
        Map<String, Integer> wordMap = analysis.analyzeWords(contentList);

        return AnalysisDto.builder()
                .deleteCnt(deleteCnt)
                .wordList(wordMap)
                .build();
    }

    @Transactional
    public void delete(Long id){
        Report report = reportRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 레포트가 없습니다."));
        reportRepository.delete(report);
    }

    @Transactional(readOnly=true)
    public AnalysisDto countInfo(Report report) {
        Long deleteCnt = requestRepository.countByEnterpriseIdAndStartDateBetween(enterpriseRepository.getOne(report.getEnterprise().getId()), report.getStartDate(), report.getEndDate());
        List<String> contentList = requestRepository.getContents(report.getEnterprise().getId(), report.getStartDate(), report.getEndDate());
        Map<String, Integer> wordMap = analysis.analyzeWords(contentList);

        return AnalysisDto.builder().deleteCnt(deleteCnt).wordList(wordMap).build();
    }

    @Transactional(readOnly = true)
    public List<ReportListResponseDto> findAllDesc() {
        return reportRepository.findAllDesc().stream()
                .map(ReportListResponseDto::new)
                .collect(Collectors.toList());
    }

}
