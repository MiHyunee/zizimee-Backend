package com.zizimee.api.pimanager.log.dto;

import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import java.time.LocalDate;
import lombok.Builder;

public class StatusSaveDto {
    private ConsentForm formId;
    private String isConsent;
    private Long signId;
    private byte[] signature;
    private LocalDate date;

    @Builder
    public StatusSaveDto(ConsentForm formId, String isConsent, Long signId, byte[] signature, LocalDate date){
        this.formId = formId;
        this.isConsent = isConsent;
        this.signId = signId;
        this.signature = signature;
        this.date = LocalDate.now();
    }

    public ConsentStatus toEntity(){
        return ConsentStatus.builder()
                .formId(formId)
                .isConsent(isConsent)
                .signId(signId)
                .signature(signature)
                .date(date)
                .build();
    }
}
