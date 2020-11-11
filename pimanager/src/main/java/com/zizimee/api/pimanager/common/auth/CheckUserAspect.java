package com.zizimee.api.pimanager.common.auth;

import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
@Aspect
public class CheckUserAspect {
    private final JwtTokenProvider jwtTokenProvider;

    @Around("@annotation(CheckUser)")
    public Object getLoginUser(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = jwtTokenProvider.getTokenFromHeader(request);
        Object loginUserId = jwtTokenProvider.getUserId(token);
        if(loginUserId != null) {
            Object proceed = joinPoint.proceed(new Object[]{loginUserId});
            return proceed;
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
