package com.zizimee.api.pimanager.log.entity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Key;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class Pem {
    //키정보를 받아서 PEM형식의 객체로 저장하고 파일로 내보내기 위한 클래스
    private PemObject pemObject;

    public Pem(Key key, String description){
        //pem 생성
        this.pemObject = new PemObject(description, key.getEncoded());
    }
    public void write(String filename) throws FileNotFoundException, IOException{
        //pem으로 저장
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        try{
            pemWriter.writeObject(this.pemObject);
        }finally {
            pemWriter.close();
        }
    }
}