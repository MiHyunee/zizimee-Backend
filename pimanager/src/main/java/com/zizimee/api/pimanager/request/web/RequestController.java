package com.zizimee.api.pimanager.request.web;

import com.zizimee.api.pimanager.common.util.Pagination;
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
    public ResponseEntity<Pagination> findAllRequestResponse(HttpServletRequest httpServletRequest,
                                                                           @RequestParam(defaultValue = "1") int nowPage) {
        final int requestCountPerPage = 5;

        String token = httpServletRequest.getHeader(HEADER_NAME);
        List<RequestResponseDto> requestResponseDto = requestService.findAllRequestResponseDesc(token);

        Pagination pagination = Pagination.builder()
                .listCount(requestResponseDto.size())
                .countPerPage(requestCountPerPage)
                .nowPage(nowPage).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(pagination);
    }

}
