package com.zizimee.api.pimanager.request.web;

import com.zizimee.api.pimanager.request.dto.*;
import com.zizimee.api.pimanager.request.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.zizimee.api.pimanager.common.jwt.JwtTokenProvider.HEADER_NAME;

@RequiredArgsConstructor
@RestController
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/request")
    public ResponseEntity save(HttpServletRequest httpServletRequest, @RequestBody RequestSaveDto requestDto) throws Throwable {
        String token = httpServletRequest.getHeader(HEADER_NAME);
        requestService.save(requestDto, token);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/request")
    public ResponseEntity<List<RequestResponseDto>> findAllRequestResponse(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader(HEADER_NAME);
        List<RequestResponseDto> requestResponseDto = requestService.findAllRequestResponseDesc(token);

        return ResponseEntity.status(HttpStatus.OK)
                .body(requestResponseDto);
    }

}
