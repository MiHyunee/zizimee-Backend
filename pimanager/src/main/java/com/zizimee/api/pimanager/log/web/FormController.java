package com.zizimee.api.pimanager.log.web;

import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.dto.FormSaveDto;
import com.zizimee.api.pimanager.log.service.FormService;
import com.zizimee.api.pimanager.log.dto.LogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@RequiredArgsConstructor
@RestController
public class FormController {
    private final FormService formService;

    @PostMapping("/form")
    public ResponseEntity save(@RequestBody FormSaveDto requestDto) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        Long id = formService.save(requestDto);
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
