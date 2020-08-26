package com.zizimee.api.pimanager.request.web;

import com.zizimee.api.pimanager.request.dto.ResponseDto;
import com.zizimee.api.pimanager.request.dto.ResponseSaveDto;
import com.zizimee.api.pimanager.request.dto.ResponseUpdateDto;
import com.zizimee.api.pimanager.request.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ResponseController {
    private final ResponseService responseService;

    @PutMapping("/response/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody ResponseUpdateDto requestDto){

        responseService.update(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.builder()
                        .id(id)
                        .build());
    }

    @GetMapping("/response/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Long id){
        ResponseDto dto = responseService.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/response")
    public ResponseEntity<List<ResponseDto>> findAll() {
        List<ResponseDto> responseList = responseService.findAllDesc();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseList);
    }
}
