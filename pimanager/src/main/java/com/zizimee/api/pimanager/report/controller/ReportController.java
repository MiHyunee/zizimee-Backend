package com.zizimee.api.pimanager.report.controller;

import com.zizimee.api.pimanager.report.dto.ReportResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



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
        return reportService.findById(id);
    }
}