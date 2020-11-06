package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.log.dto.StatusSaveDto;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import com.zizimee.api.pimanager.log.entity.StatusRepository;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class StatusService {
    private StatusRepository statusRepository;


    /*public Long save(StatusSaveDto requestDto) throws NoSuchPaddingException, NoSuchAlgorithmException{

        Security.addProvider(new BouncyCastleProvider());



        File privKeyFile = new File("/resources/private.key");
        PrivateKey privateKey = null;

        //파일에서 priv키 읽어오기
        Path privKeyFile = Paths.get("src/main/resources/PEM");


        FileInputStream privateFis = new FileInputSteam(privKeyFile);
        FileInputStream cipher = Cipher.getInstance("RSA");
        ByteArrayOutputStream privKeyBaos = new ByteArrayInputStream();
        int curByte1 = 0;
        while((curByte1 = privateFis.read()) != -1){
            privKeyBaos.write(curByte1);
        }
        PKCS8EncodedKeySpec privKeySpec
                = new PKCS8EncodedKeySpec(privKeyBaos.toByteArray());
        privKeyBaos.close();

        return  statusRepository.save(requestDto.toEntity()).getId();
    }

    public String signVerify(Long signId, PublicKey pub) throws GeneralSecurityException {
        ConsentStatus cs = statusRepository.findBySignId(signId);
        String status = cs.getIsConsent();
        String item = cs.getFormId().getConsentItem();
        String message = status+item;
        byte[] signature = cs.getSignature();

        boolean verified = verify(pub, signature, message.getBytes());
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
    }*/
}
