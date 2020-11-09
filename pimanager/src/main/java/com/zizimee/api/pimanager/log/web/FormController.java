package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequiredArgsConstructor
@RestController
public class FormController {
    private final FormService formService;

    @PostMapping("/keyPair")
    public FormResponseDto save() throws IOException, NoSuchAlgorithmException {
        return formService.save();
    }

    @PostMapping("/form")
    public ResponseEntity update(@RequestBody byte[] form, Long id) throws Exception {
        formService.update(form, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(FormResponseDto.builder().id(id).build());
    }
}
