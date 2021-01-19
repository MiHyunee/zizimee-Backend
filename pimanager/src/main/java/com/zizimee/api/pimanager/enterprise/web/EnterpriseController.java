package com.zizimee.api.pimanager.enterprise.web;

import com.zizimee.api.pimanager.common.auth.CheckEnt;
import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.enterprise.dto.*;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/enterprise")
@RestController
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody RequestSignUpDto requestSignUpDto) throws Exception {

        enterpriseService.signUp(requestSignUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseEnterpriseDto> signIn(HttpServletRequest request, @RequestBody RequestSignInDto requestSignInDto) {
        try {
            String token = request.getHeader(JwtTokenProvider.HEADER_NAME);
            ResponseEnterpriseDto dto;
            if(token != null) {
                dto = enterpriseService.loginByToken(token);
            } else {
                dto = enterpriseService.loginByPassword(requestSignInDto);
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
    public ResponseEntity genTempPwAndSendMail(@RequestBody RequestTempPwDto requestTempPwDto) {
        try {
            return enterpriseService.genTempPwAndSendMail(requestTempPwDto);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/verify-email-token")
    public ResponseEntity verifyEmailToken(@RequestParam("token") String token, @RequestParam("email") String email) {
        return enterpriseService.verifyEmailToken(token, email);
    }

    @PostMapping("/changePw")
    public ResponseEntity changePw(@CheckEnt Enterprise enterprise, @RequestBody RequestChangePwDto requestChangePwDto) {
        String password = requestChangePwDto.getPassword();
        return enterpriseService.changePw(enterprise, password);
    }
}
