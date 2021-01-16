package com.zizimee.api.pimanager.enterprise.service;

import com.zizimee.api.pimanager.common.auth.PasswordEncoder;
import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.common.mail.MailService;
import com.zizimee.api.pimanager.enterprise.dto.*;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class EnterpriseService implements UserDetailsService {

    public final EnterpriseRepository enterpriseRepository;
    public final JwtTokenProvider jwtTokenProvider;
    public final PasswordEncoder passwordEncoder;
    public final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Enterprise enterprise = enterpriseRepository.getOne(Long.valueOf(id));
        if(!enterprise.getId().equals(Long.valueOf(id))) {
            throw new UsernameNotFoundException("INVALID REQUEST");
        }
        return org.springframework.security.core.userdetails.User.builder().username(id).password("").roles("").build();
    }


    @Transactional
    public void signUp(RequestSignUpDto requestSignUpDto) throws Exception {
        if(enterpriseRepository.findByRegisterNmb(requestSignUpDto.getRegisterNmb()) == null)
            throw new Exception("Already used name");
        if(enterpriseRepository.findBySignUpId(requestSignUpDto.getSignUpId()) == null)
            throw new Exception("Already used id");
        Enterprise enterprise = requestSignUpDto.toEntity();
        String password = requestSignUpDto.getPassword();
        String salt = passwordEncoder.genSalt();
        enterprise.setSalt(salt);
        enterprise.setPassword(passwordEncoder.encodePassword(password, salt));
        enterprise.genTempVerifyingEmailToken();

        MimeMessage message = mailService.createSignUpMessage(enterprise);
        mailService.send(message);

        enterpriseRepository.save(enterprise);
    }

    public ResponseEnterpriseDto loginByToken(String token) throws Exception {
        if(jwtTokenProvider.validateToken(token)) {
            Enterprise enterprise = enterpriseRepository.getOne(Long.valueOf(jwtTokenProvider.getUserId(token)));

            return ResponseEnterpriseDto.builder()
                    .name(enterprise.getName())
                    .build();
        } else {
            throw new Exception("invalid token");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEnterpriseDto loginByPassword(RequestSignInDto requestSignInDto) throws Exception {
        Enterprise enterprise = enterpriseRepository.findBySignUpId(requestSignInDto.getSignUpId()).orElseThrow(()-> new IllegalArgumentException("해당 아이다가 없습니다."));
        if(enterprise==null) {
            throw new Exception("INVALID ID");
        }

        String salt = enterprise.getSalt();
        String password = passwordEncoder.encodePassword(requestSignInDto.getPassword(), salt);
        if(!enterprise.getPassword().equals(password)) {
            throw new Exception("INVALID PASSWORD");
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

    @Transactional
    public ResponseEntity genTempPwAndSendMail(RequestFindPwDto requestFindPwDto) throws MessagingException {
        Enterprise enterprise = enterpriseRepository.findByRegisterNmbAndSignUpId(requestFindPwDto.getRegisterNmb(), requestFindPwDto.getSignUpId())
                .orElseThrow(()-> new IllegalArgumentException("Invalid request"));
        if(!enterprise.isEmailVerified()) {
            throw new IllegalArgumentException("verify email first");
        }
        String tempPw = genTempPw();
        String salt = enterprise.getSalt();
        enterprise.setPassword(passwordEncoder.encodePassword(tempPw, salt));
        MimeMessage message = mailService.createTempPwMessage(tempPw, enterprise.getEmail());
        mailService.send(message);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private String genTempPw() {
        String tempPw = "";
        char[] pwCollection = new char[]{
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                'A', 'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                '!','@','#','$','%','^','&','*','(',')'};

        for(int i=0; i<8; i++) {
            int randPw = (int)(Math.random()*(pwCollection.length));
            tempPw += pwCollection[randPw];
        }
        return tempPw;
    }

    @Transactional
    public ResponseEntity verifyEmailToken(String token, String email) {
        Enterprise enterprise = enterpriseRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Email"));
        if(enterprise.getEmailVerifyingToken().equals(token)) {
            enterprise.setEmailVerified(true);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
