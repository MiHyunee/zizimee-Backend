package com.zizimee.api.pimanager.common.auth;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@RequiredArgsConstructor
@Component
@Aspect
public class CheckEntAspect {
    private final EnterpriseRepository enterpriseRepository;

    @Around("execution(* *(.., @CheckEnt (*), ..))")
    public Object getLoginEnt(ProceedingJoinPoint joinPoint) throws Throwable {
        String entId = SecurityContextHolder.getContext().getAuthentication().getName();
        Enterprise enterprise = enterpriseRepository.findById(Long.parseLong(entId))
                .orElseThrow(()->new IllegalArgumentException("@CheckEnt NULL"));

        if(enterprise != null) {
            return joinPoint.proceed(new Enterprise[] {enterprise});
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
