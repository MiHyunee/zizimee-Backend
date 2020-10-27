package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.log.dto.FormSaveDto;
import com.zizimee.api.pimanager.log.entity.FormRepository;
import com.zizimee.api.pimanager.log.entity.Pem;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;


    @Transactional
    public Long save(FormSaveDto requestDto) throws IOException, NoSuchAlgorithmException, NoSuchProviderException{
        String entId = requestDto.getEnterpriseId().toString();
        String formId = reqeustDto.getFormId().toString();
        String dirPath = "/resources"+entId+formId;

        Security.addProvider(new BouncyCastleProvider());
        //키쌍 생성
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA","BC");
        generator.initialize(1024);

        KeyPair keyPair = generator.generateKeyPair();
        RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey pub = (RSAPublicKey) keyPair.getPublic();

        //키를 각각 PEM으로 저장
        Pem privPemFile =  new Pem(priv, "RSA PRIVATE KEY");
        privPemFile.write(dirPath+"priv.pem");
        Pem pubPemFile =  new Pem(pub, "RSA PUBLIC KEY");
        privPemFile.write(dirPath+"pub.pem");

        return formRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public void KeyPair

    /*@Transactional
    public void update(Long id, LogUpdateDto requestDto ){
         Log log = logRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        User userId = userRepository.findByName(requestDto.getName());
        log.update(userId, requestDto.getIntend(), requestDto.getProvidedInfo(),
                requestDto.getThirdParty(), requestDto.getUseDate());
    }*/

}
