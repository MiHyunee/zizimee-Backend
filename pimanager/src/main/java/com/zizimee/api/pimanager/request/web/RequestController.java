package com.zizimee.api.pimanager.request.web;

import com.zizimee.api.pimanager.request.dto.*;
import com.zizimee.api.pimanager.request.service.RequestService;
import com.zizimee.api.pimanager.request.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RequestController {

    private final RequestService requestService;
    private final ResponseService responseService;

    @PostMapping("/request")
    public ResponseEntity save(@RequestBody RequestSaveDto requestDto){
        Long id = requestService.save(requestDto);
        ResponseSaveDto responseDto = new ResponseSaveDto();
        responseService.save(responseDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RequestResponseDto.builder().id(id).build());
    }

    @PutMapping("/request/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody RequestUpdateDto requestDto){

        requestService.update(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(RequestResponseDto.builder()
                        .id(id)
                        .build());
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<RequestResponseDto> findById(@PathVariable Long id){
        RequestResponseDto dto = requestService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/request")
    public ResponseEntity<List<RequestResponseDto>> findAll() {
        List<RequestResponseDto> requestList = requestService.findAllDesc();

        return ResponseEntity.status(HttpStatus.OK)
                .body(requestList);
    }
}
