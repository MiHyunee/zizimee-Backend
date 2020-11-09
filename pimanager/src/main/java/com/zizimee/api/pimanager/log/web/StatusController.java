package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.dto.StatusResponseDto;
import com.zizimee.api.pimanager.log.dto.StatusSaveDto;
import com.zizimee.api.pimanager.log.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class StatusController {
    private StatusService statusService;

    @PostMapping("/status")
    public ResponseEntity save(byte[] isConsent, Long signId, byte[] signature, LocalDate date) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        Long id = statusService.save(isConsent, signId, signature, date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(FormResponseDto.builder().id(id).build());
    }

    @PostMapping("/signVerify")
    public String signVerify(@RequestBody SignDto signDto) throws GeneralSecurityException {
        return statusService.signVerify(signDto);
    }
}
