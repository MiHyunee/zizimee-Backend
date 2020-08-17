package com.zizimee.api.pimanager.user.web;

import com.zizimee.api.pimanager.user.dto.RequestJwtAuthenticationDto;
import com.zizimee.api.pimanager.user.dto.RequestSignUpDto;
import com.zizimee.api.pimanager.user.dto.ResponseSignUpDto;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import com.zizimee.api.pimanager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserRepository userRepository;
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
    public ResponseEntity<ResponseSignUpDto> loginByToken(@RequestBody RequestJwtAuthenticationDto request){
        try {
            return userService.loginByToken(request.getToken());
        } catch (Exception e) {
            return (ResponseEntity<ResponseSignUpDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

}
