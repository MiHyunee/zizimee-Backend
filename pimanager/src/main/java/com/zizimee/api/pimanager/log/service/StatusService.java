package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentFormRepository;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import com.zizimee.api.pimanager.log.entity.ConsentStatusRepository;

@RequiredArgsConstructor
@Service
public class StatusService {
    private final ConsentStatusRepository statusRepository;
    private final ConsentFormRepository formRepository;

    @Transactional
    public void save(byte[] isConsent, Long signId, byte[] signature) throws Throwable {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Enterprise enterprise =(Enterprise)principle;
        Long entId = enterprise.getId();
        ConsentForm form = (ConsentForm)formRepository.findRecentByEntId(entId)
                .orElseThrow(()->new IllegalArgumentException("해당 기업의 form 없습니다"));
        FileInputStream privateFis = new FileInputStream("src\\main\\resourves\\"+entId+"private.key");
        ByteArrayOutputStream privKeyBaos = new ByteArrayOutputStream();
        int curByte1 = 0;
        while ((curByte1 = privateFis.read()) != -1) {
            privKeyBaos.write(curByte1);
        }
        PKCS8EncodedKeySpec privKeySpec
                = new PKCS8EncodedKeySpec(privKeyBaos.toByteArray());
        privKeyBaos.close();
        KeyFactory fac = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = fac.generatePrivate(privKeySpec);
        //복호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        String status = byteToString(cipher.doFinal(isConsent));
        //저장
        ConsentStatus consentStatus = ConsentStatus.builder()
                .formId(form)
                .signId(signId)
                .signature(signature)
                .isConsent(status)
                .build();
        statusRepository.save(consentStatus);
    }

    @Transactional
    public String signVerify(SignDto requestDto) throws GeneralSecurityException {
        ConsentStatus cs = statusRepository.findBySignId(requestDto.getSignId()).orElseThrow(()-> new IllegalArgumentException("동의 여부 상태 정보가 없습니다"));
        String status = cs.getIsConsent();
        String item = cs.getFormId().getConsentItem();
        String message = status+item;
        byte[] signature = cs.getSignature();

        boolean verified = verify(requestDto.getPub(), signature, message.getBytes());
        if(verified)
            return "검증이 완료되었습니다.";
        else
            return "검증에 실패하였습니다.";
    }

    public boolean verify(PublicKey pub, byte[] signature, byte[] message)
        throws GeneralSecurityException{
        Signature sig = Signature.getInstance("SHA256withRSA/PSS");
        sig.initVerify(pub);
        sig.update(message);
        return sig.verify(signature);
    }

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
