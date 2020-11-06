package com.zizimee.api.pimanager.log.web;

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

import javax.crypto.NoSuchPaddingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class StatusController {
    private StatusService statusService;

    @PostMapping("/status")
    public ResponseEntity save(@RequestBody StatusSaveDto requestDto) throws NoSuchAlgorithmException, NoSuchPaddingException {
        Long id = statusService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StatusResponseDto.builder().id(id).build());
    }

    @PostMapping("/signVerify")
    public String requestVerify(@RequestBody SignDto signDto) throws GeneralSecurityException {
        String result = statusService.signVerify(signDto.getSignId(), signDto.getPub());
        return ResponseEntity.status(HttpStatus.OK)
                .body()
    }
}
