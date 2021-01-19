package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.consentManagement.entity.EntLinkage;
import com.zizimee.api.pimanager.consentManagement.entity.EntLinkageRepository;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.log.dto.ConsentStatusRequestDto;
import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentFormRepository;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import lombok.RequiredArgsConstructor;
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
    private final EntLinkageRepository linkageRepository;
    private final static String RESOURCE_PATH = "pimanager\\src\\main\\resources\\";

    @Transactional
    public void save(Enterprise enterprise, ConsentStatusRequestDto consentStatusRequestDto) throws Throwable {
        Long entId = enterprise.getId();
        ConsentForm form = formRepository.findRecentByEntId(entId)
                .orElseThrow(()->new IllegalArgumentException("해당 기업의 form 없습니다"));
        String path = RESOURCE_PATH + entId;
        FileInputStream privateInputStream = new FileInputStream(path + "\\private.key");
        ByteArrayOutputStream privatekeyOutputStream = new ByteArrayOutputStream();
        int curByte1 = 0;
        while ((curByte1 = privateInputStream.read()) != -1) {
            privatekeyOutputStream.write(curByte1);
        }
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privatekeyOutputStream.toByteArray());
        privatekeyOutputStream.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        //복호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        String status = byteToString(cipher.doFinal(consentStatusRequestDto.getIsConsent()));
        EntLinkage link = linkageRepository.findByEntIdANDUid(enterprise, consentStatusRequestDto.getUid())
                .orElseThrow(()-> new IllegalArgumentException("연동 기록이 없습니다"));
        ConsentStatus consentStatus = ConsentStatus.builder()
                .formId(form)
                .linkId(link)
                .signId(consentStatusRequestDto.getSignId())
                .signature(consentStatusRequestDto.getSignature())
                .isConsent(status)
                .build();
        statusRepository.save(consentStatus);
    }

    @Transactional
    public String signVerify(SignDto requestDto) throws GeneralSecurityException {
        ConsentStatus consentStatus = statusRepository.findBySignId(requestDto.getSignId())
                .orElseThrow(()-> new IllegalArgumentException("동의 여부 상태 정보가 없습니다"));
        String status = consentStatus.getIsConsent();
        String item = consentStatus.getFormId().getConsentItem();
        String message = status+item;
        byte[] signature = consentStatus.getSignature();

        boolean verified = verify(requestDto.getPub(), signature, message.getBytes());
        if(verified)
            return "검증이 완료되었습니다.";
        else
            return "검증에 실패하였습니다.";
    }

    public boolean verify(PublicKey pub, byte[] signature, byte[] message) throws GeneralSecurityException{
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
