package com.zizimee.api.pimanager.request.web;

import com.zizimee.api.pimanager.common.auth.CheckUser;
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

    @CheckUser
    @PostMapping("/request")
    public ResponseEntity save(@RequestBody RequestSaveDto requestDto, @RequestParam String loginUserId) throws Throwable {
        requestService.save(requestDto, loginUserId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CheckUser
    @GetMapping("/request")
    public ResponseEntity<List<RequestResponseDto>> findAllRequestResponse(@RequestParam String loginUserId) {

        List<RequestResponseDto> requestResponseDto = requestService.findAllRequestResponseDesc(loginUserId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(requestResponseDto);
    }

}
