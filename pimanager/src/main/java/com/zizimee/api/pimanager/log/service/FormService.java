package com.zizimee.api.pimanager.log.service;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.log.dto.FormResponseDto;
import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentFormRepository;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

@RequiredArgsConstructor
@Service
public class FormService {
    private final ConsentFormRepository formRepository;

    @Transactional
    public FormResponseDto save(Enterprise enterprise) throws NoSuchAlgorithmException, IOException {
        ConsentForm consentForm = new ConsentForm(enterprise);
        //키쌍생성
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.genKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privKey = keyPair.getPrivate();

        formRepository.save(consentForm).getId();

        //키를 저장합니다.
        Long entId = enterprise.getId();
        String path = "pimanager\\src\\main\\resources\\"+ entId;
        File makeFolder = new File(path);
        boolean wasSuccessful = makeFolder.mkdir();
        if(!wasSuccessful)
            System.out.println("존재하는 디렉토리입니다.");

        FileOutputStream publicFos = new FileOutputStream(path+"\\public.key");
        byte[] bytePublic = pubKey.getEncoded();
        publicFos.write(pubKey.getEncoded());
        publicFos.close();

        FileOutputStream privateFos = new FileOutputStream(path+"\\private.key");
        privateFos.write(privKey.getEncoded());
        privateFos.close();

        return new FormResponseDto(bytePublic);
    }

    @Transactional
    public void update(Enterprise enterprise, byte[] encodedItem) throws Throwable {
        //privateKey 읽어오기
        Long entId = enterprise.getId();
        ConsentForm consentForm = formRepository.findRecentByEntId(entId)
                .orElseThrow(()-> new IllegalArgumentException("해당 기업의 form이 없습니다."));
        FileInputStream privateFis = new FileInputStream("src\\main\\resources\\"+entId+"\\private.key");
        ByteArrayOutputStream privKeyBaos = new ByteArrayOutputStream();
        int curByte1 = 0;
        while ((curByte1 = privateFis.read()) != -1)
            privKeyBaos.write(curByte1);

        PKCS8EncodedKeySpec privKeySpec
                = new PKCS8EncodedKeySpec(privKeyBaos.toByteArray());
        privKeyBaos.close();
        KeyFactory fac = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = fac.generatePrivate(privKeySpec);
        //복호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        String content = byteToString(cipher.doFinal(encodedItem));
        consentForm.update(content);
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
