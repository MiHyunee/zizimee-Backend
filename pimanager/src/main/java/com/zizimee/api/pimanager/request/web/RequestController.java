package com.zizimee.api.pimanager.request.web;

import com.zizimee.api.pimanager.request.dto.*;
import com.zizimee.api.pimanager.request.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/request")
    public ResponseEntity save (String loginUserId, RequestSaveDto dto) throws Throwable {
        requestService.save(dto, loginUserId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/request")
    public ResponseEntity<List<RequestResponseDto>> findAllRequestResponse(String loginUserId) {

        List<RequestResponseDto> requestResponseDto = requestService.findAllRequestResponseDesc(loginUserId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(requestResponseDto);
    }
}