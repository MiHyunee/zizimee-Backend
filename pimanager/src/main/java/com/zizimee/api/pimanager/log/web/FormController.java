package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.dto.FormSaveDto;
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
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@RequiredArgsConstructor
@RestController
public class FormController {
    private final FormService formService;

    @PostMapping("/form")
    public ResponseEntity save(@RequestBody byte[] encodedItem) throws NoSuchAlgorithmException, IOException, NoSuchProviderException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException {
        Long id = formService.save(encodedItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FormResponseDto.builder().id(id).build());
    }

    /*@PutMapping("/log/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody LogUpdateDto requestDto){
        logService.update(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(LogResponseDto.builder()
                        .id(id)
                        .build());
    }*/



}
