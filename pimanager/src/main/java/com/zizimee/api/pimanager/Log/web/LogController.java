package com.zizimee.api.pimanager.Log.web;

import com.zizimee.api.pimanager.Log.dto.LogResponseDto;
import com.zizimee.api.pimanager.Log.dto.LogSaveDto;
import com.zizimee.api.pimanager.Log.dto.LogUpdateDto;
import com.zizimee.api.pimanager.Log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LogController {
    private final LogService logService;

    @PostMapping("/log")
    public ResponseEntity save(@RequestBody LogSaveDto requestDto){
        Long id = logService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LogResponseDto.builder().id(id).build());
    }

    @PutMapping("/log/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody LogUpdateDto requestDto){
        logService.update(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(LogResponseDto.builder()
                        .id(id)
                        .build());
    }



}
