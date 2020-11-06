package com.zizimee.api.pimanager.enterprise.web;

import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.enterprise.dto.*;
import com.zizimee.api.pimanager.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/enterprise")
@RestController
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody RequestSignUpDto requestSignUpDto) throws Exception {

        Long id = enterpriseService.signUp(requestSignUpDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseEnterpriseDto.builder()
                .id(id)
                .build());
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseEnterpriseDto> signIn(HttpServletRequest request, @RequestBody RequestSignInDto requestSignInDto) throws Exception {
        try {
            String token = request.getHeader(JwtTokenProvider.HEADER_NAME);
            ResponseEnterpriseDto dto;
            if(token != null) {
                dto = enterpriseService.loginByToken(token);
            } else {
                dto = enterpriseService.loginByOauth(requestSignInDto);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(dto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/idInquiry")
    public ResponseEntity<ResponseEnterpriseDto> findId(@RequestBody RequestFindIdDto requestFindIdDto) {
        ResponseEnterpriseDto dto = enterpriseService.findId(requestFindIdDto);

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/pwInquiry")
    public ResponseEntity<ResponseEnterpriseDto> findPw(@RequestBody RequestFindPwDto requestFindPwDto) {
        ResponseEnterpriseDto dto = enterpriseService.findPw(requestFindPwDto);

        return new ResponseEntity(dto, HttpStatus.OK);
    }

}
