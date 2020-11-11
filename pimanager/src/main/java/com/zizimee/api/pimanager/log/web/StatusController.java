package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.GeneralSecurityException;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class StatusController {
    private StatusService statusService;

    @PostMapping("/status")
    public ResponseEntity save(byte[] isConsent, Long signId, byte[] signature, LocalDate date) throws Exception {
        Long id = statusService.save(isConsent, signId, signature, date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(FormResponseDto.builder().id(id).build());
    }


    @PostMapping("/signVerify")
    public String signVerify(@RequestBody SignDto signDto) throws GeneralSecurityException {
        return statusService.signVerify(signDto);
    }

}
