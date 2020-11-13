package com.zizimee.api.pimanager.report.service;

import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.log.entity.ConsentFormRepository;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import com.zizimee.api.pimanager.log.entity.ConsentStatusRepository;
import com.zizimee.api.pimanager.report.dto.ReportSaveRequestDto;
import com.zizimee.api.pimanager.report.entity.Report;
import com.zizimee.api.pimanager.report.entity.ReportRepository;
import com.zizimee.api.pimanager.request.entity.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ConsentFormRepository formRepository;
    private final ConsentStatusRepository statusRepository;
/*
    @Transactional
    public void report() {
        Long formId = formRepository.findRecentByEntId(entId).getId();
        List<ConsentStatus> status = statusRepository.findByFormIdAndDate(formId, start, end);
        int disagree=0, agree=0;
        for(ConsentStatus s : status){
            String isConsent = s.getIsConsent();
            for(int i=0;i<isConsent.length();i++){
                if(isConsent.charAt(i)==0)
                    disagree++;
                else
                    agree++
            }
        }
        return reportRepository.save()
    }*/


}