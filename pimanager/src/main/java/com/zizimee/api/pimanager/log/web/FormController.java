package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.common.auth.CheckEnt;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class FormController {
    private final FormService formService;

    @PostMapping("/keyPair")
    public ResponseEntity<FormResponseDto> save(@CheckEnt Enterprise enterprise) throws IOException, NoSuchAlgorithmException {
        System.out.println(enterprise.getId());
        FormResponseDto formResponseDto = formService.save(enterprise);

        return ResponseEntity.status(HttpStatus.OK)
                .body(formResponseDto);
    }

    @PostMapping("/form")
    public ResponseEntity update(@CheckEnt Enterprise enterprise, @RequestBody byte[] form) throws Throwable {
        formService.update(enterprise, form);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
