package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.common.auth.CheckUser;
import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class FormController {
    private final FormService formService;

    @CheckUser
    @PostMapping("/keyPair")
    public ResponseEntity<FormResponseDto> save(@RequestParam Long entId) throws IOException, NoSuchAlgorithmException {
        FormResponseDto formResponseDto = formService.save(entId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(formResponseDto);
    }

    @PostMapping("/form")
    public ResponseEntity update(@RequestParam Long entId, @RequestBody byte[] form) throws Throwable {
        formService.update(entId, form);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
