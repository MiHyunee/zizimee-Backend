package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.common.auth.CheckEnt;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.dto.ConsentStatusRequestDto;
import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@RestController
public class StatusController {
    private final StatusService statusService;

    @PostMapping("/status")
    public ResponseEntity save(@CheckEnt Enterprise enterprise, @RequestBody ConsentStatusRequestDto consentStatusRequestDto) throws Throwable {
        statusService.save(enterprise, consentStatusRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/signVerify")
    public String signVerify(@RequestBody SignDto signDto) throws GeneralSecurityException {
        return statusService.signVerify(signDto);
    }
}
