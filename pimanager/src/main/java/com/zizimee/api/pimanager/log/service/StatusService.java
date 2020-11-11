package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.log.dto.SignDto;
import com.zizimee.api.pimanager.log.dto.StatusSaveDto;
import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import com.zizimee.api.pimanager.log.entity.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import com.zizimee.api.pimanager.log.entity.ConsentStatusRepository;

@RequiredArgsConstructor
@Service
public class StatusService {
    private final ConsentStatusRepository statusRepository;
    private final FormRepository formRepository;

    @Transactional
    public Long save(byte[] isConsent, Long signId, byte[] signature, LocalDate date) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //privateKey 읽어오기
        Long entId = "";
        ConsentForm formId = formRepository.findByEnterpriseId(entId).findRecent(entId);
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
        StatusSaveDto requestDto = new StatusSaveDto(formId, status, signId, signature,date);
        return statusRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public String signVerify(SignDto requestDto) throws GeneralSecurityException {
        ConsentStatus cs = statusRepository.findBySignId(requestDto.getSignId());
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
