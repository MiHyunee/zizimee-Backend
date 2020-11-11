package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

@RequiredArgsConstructor
@RestController
public class FormController {
    private final FormService formService;

    @PostMapping("/keyPair")
    public PublicKey save() throws IOException, NoSuchAlgorithmException {
        return formService.save();
    }

    @PutMapping("/form")
    public ResponseEntity update(@RequestBody byte[] form) throws Exception{
        formService.update(form);
        return ResponseEntity.status(HttpStatus.OK)
                .body(FormResponseDto.builder().id(id).build());
    }
}
