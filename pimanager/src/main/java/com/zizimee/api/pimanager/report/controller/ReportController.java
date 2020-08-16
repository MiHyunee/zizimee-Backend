package com.zizimee.api.pimanager.report.controller;

import com.zizimee.api.pimanager.report.dto.ReportListResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/report")
    public Long save(@RequestBody ReportSaveRequestDto requestDto){
        return reportService.save(requestDto);
    }

    @DeleteMapping("/report/{id}")
    public Long delete(@PathVariable Long id){
        reportService.delete(id);
        return id;
    }

    @GetMapping("/report/{id}")
    public ReportResponseDto findById(@PathVariable Long id){

        return  reportService.findById(id);
    }

    @GetMapping("/report")
    public List<ReportListResponseDto> findAll() {

        return reportService.findAllDesc();
    }
}