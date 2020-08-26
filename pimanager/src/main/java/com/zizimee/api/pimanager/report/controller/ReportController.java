package com.zizimee.api.pimanager.report.controller;

import com.zizimee.api.pimanager.report.dto.AnalysisDto;
import com.zizimee.api.pimanager.report.dto.ReportListResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.entity.Report;
import com.zizimee.api.pimanager.report.entity.ReportRepository;
import com.zizimee.api.pimanager.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    @PostMapping("/report")
    public ResponseEntity<ReportResponseDto> save(@RequestBody ReportSaveRequestDto requestDto){
        AnalysisDto analysisDto = reportService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReportResponseDto.builder()
                        .startDate(requestDto.getStartDate())
                        .endDate(requestDto.getEndDate())
                        .deleteCnt(analysisDto.getDeleteCnt())
                        .wordList(analysisDto.getWordList())
                        .build());
    }

    @DeleteMapping("/report/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        reportService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ReportResponseDto> findById(@PathVariable Long id) throws Exception {
        Report report = reportRepository.getOne(id);
        AnalysisDto analysisDto;
        if(report==null) {
            throw new Exception("Invalid Request");
        } else {
            analysisDto = reportService.countInfo(report);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ReportResponseDto.builder()
                        .startDate(report.getStartDate())
                        .endDate(report.getEndDate())
                        .enterpriseName(report.getEnterprise().getName())
                        .deleteCnt(analysisDto.getDeleteCnt())
                        .wordList(analysisDto.getWordList())
                        .build());
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportListResponseDto>> findAll() {

        return ResponseEntity.status(HttpStatus.OK)
        .body(reportService.findAllDesc());
    }
}