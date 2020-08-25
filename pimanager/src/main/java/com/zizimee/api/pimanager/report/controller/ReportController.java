package com.zizimee.api.pimanager.report.controller;

import com.zizimee.api.pimanager.report.dto.ReportResponseDto;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/report")
    public ResponseEntity save(@RequestBody ReportSaveRequestDto requestDto){
        Long id = reportService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReportResponseDto.builder()
                        .id(id).build());
    }

    @DeleteMapping("/report/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        reportService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/report/{id}")
    public String findById(@PathVariable Long id){

        return  reportService.findById(id);
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportResponseDto>> findAll() {
        List<ReportResponseDto> noticeList = reportService.findAllDesc();

        return ResponseEntity.status(HttpStatus.OK)
                .body(noticeList);
    }
}