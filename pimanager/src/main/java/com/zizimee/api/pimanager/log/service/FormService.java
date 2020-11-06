package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.dto.FormSaveDto;
import com.zizimee.api.pimanager.log.entity.FormRepository;
import com.zizimee.api.pimanager.log.entity.Pem;
import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;



    @Transactional
    public Long save(byte[] encodedItem) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //String jwt = request.getHeader(JwtTokenProvider.HEADER_NAME);
        Enterprise entId = ;
        //privateKey 읽어오기
        File privateKeyFile = new File("src/main/resources/private.key");
        PrivateKey privateKey = null;
        Path privateFile = Paths.get("privateFile");
        byte[] privateKeyBytes = Files.readAllBytes(privateFile);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(new X509EncodedKeySpec(privateKeyBytes));
        //복호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        String content = byteToString(cipher.doFinal(encodedItem));
        FormSaveDto requestDto = new FormSaveDto();
        return formRepository.save(requestDto.toEntity(content, entId)).getId();
    }

    //@Transactional
    //public void KeyPair

    /*@Transactional
    public void update(Long id, LogUpdateDto requestDto ){
         Log log = logRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        User userId = userRepository.findByName(requestDto.getName());
        log.update(userId, requestDto.getIntend(), requestDto.getProvidedInfo(),
                requestDto.getThirdParty(), requestDto.getUseDate());
    }*/

    public static String byteToString(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<b.length; i++)
            sb.append(byteToBinaryString(b[i]));
        return sb.toString();
    }

    public static String byteToBinaryString(byte n){
        StringBuilder sb = new StringBuilder("00000000");
        for(int bit=0;bit<8;bit++){
            if(((n>>bit)&1)>0)
                sb.setCharAt(7-bit,'1');
        }
        return sb.toString();
    }

}
