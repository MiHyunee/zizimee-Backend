package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.common.auth.CheckUser;
import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@RestController
public class StatusController {
    private StatusService statusService;

    @CheckUser
    @PostMapping("/status")
    public ResponseEntity save(@RequestParam Long entId, byte[] isConsent, Long signId, byte[] signature) throws Throwable {
        statusService.save(entId, isConsent, signId, signature);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/signVerify")
    public String signVerify(@RequestBody SignDto signDto) throws GeneralSecurityException {
        return statusService.signVerify(signDto);
    }

}
