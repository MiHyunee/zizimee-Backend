package com.zizimee.api.pimanager.user.web;

import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.user.dto.RequestSignUpDto;
import com.zizimee.api.pimanager.user.dto.ResponseSignUpDto;
import com.zizimee.api.pimanager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseSignUpDto> verifyIdToken(@RequestBody RequestSignUpDto request) {
        try {
            return userService.verifyIdToken(request);
        } catch (Exception e) {
            return (ResponseEntity<ResponseSignUpDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseSignUpDto> loginByToken(HttpServletRequest request){
        try {
            String token = request.getHeader(JwtTokenProvider.HEADER_NAME);
            return userService.loginByToken(token);
        } catch (Exception e) {
            return (ResponseEntity<ResponseSignUpDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

}
