package com.zizimee.api.pimanager.report.web;

import com.zizimee.api.pimanager.report.entity.ReportRepository;
import com.zizimee.api.pimanager.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    public void report(){

    }


}