package com.zizimee.api.pimanager.enterprise.service;

import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.enterprise.dto.*;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EnterpriseService implements UserDetailsService {

    public final EnterpriseRepository enterpriseRepository;
    public final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Enterprise enterprise = enterpriseRepository.getOne(Long.valueOf(id));
        if(enterprise.getId().equals(Long.valueOf(id))) {
            throw new UsernameNotFoundException("INVALID REQUEST");
        }
        return org.springframework.security.core.userdetails.User.builder().username(id).build();
    }


    public Long signUp(RequestSignUpDto requestSignUpDto) throws Exception {
        if(enterpriseRepository.findByRegisterNmb(requestSignUpDto.getRegisterNmb()) == null)
            throw new Exception("Already used name");
        if(enterpriseRepository.findBySignUpId(requestSignUpDto.getSignUpId()) == null)
            throw new Exception("Already used id");
        Enterprise enterprise = enterpriseRepository.save(requestSignUpDto.toEntity());

        return enterprise.getId();
    }

    public ResponseEnterpriseDto loginByToken(String token) throws Exception {
        if(jwtTokenProvider.validateToken(token)) {
            Enterprise enterprise = enterpriseRepository.getOne(Long.valueOf(jwtTokenProvider.getUserId(token)));

            return ResponseEnterpriseDto.builder()
                    .name(enterprise.getName())
                    .build();
        } else {
            throw new Exception("invaid token");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEnterpriseDto loginByOauth(RequestSignInDto requestSignInDto) throws Exception {
        Enterprise enterprise = enterpriseRepository.findBySignUpId(requestSignInDto.getSignUpId()).orElseThrow(()-> new IllegalArgumentException("해당 아이다가 없습니다."));
        if(enterprise==null) {
            throw new Exception("INVALID ID");
        }
        if(!enterprise.getPassword().equals(requestSignInDto.getPassword())) {
            throw new Exception("INVLAID PASSWORD");
        }
        String newToken = jwtTokenProvider.createToken(enterprise.getId().toString());
        return ResponseEnterpriseDto.builder()
                .id(enterprise.getId())
                .name(enterprise.getName())
                .token(newToken)
                .build();

    }

    @Transactional(readOnly = true)
    public ResponseEnterpriseDto findId(RequestFindIdDto requestFindIdDto) {
        Enterprise enterprise = enterpriseRepository.findByRegisterNmb(requestFindIdDto.getRegisterNmb()).orElseThrow(()-> new IllegalArgumentException("Wrong RegisterNumber"));

        return ResponseEnterpriseDto.builder()
                .signUpId(enterprise.getSignUpId())
                .build();
    }

    @Transactional(readOnly = true)
    public ResponseEnterpriseDto findPw(RequestFindPwDto requestFindPwDto) {
        Enterprise enterprise = enterpriseRepository.findByRegisterNmbAndSignUpId(requestFindPwDto.getRegisterNmb(), requestFindPwDto.getSignUpId())
                .orElseThrow(()-> new IllegalArgumentException("Invalid request"));

        return ResponseEnterpriseDto.builder()
                .password(enterprise.getPassword())
                .build();
    }
}
