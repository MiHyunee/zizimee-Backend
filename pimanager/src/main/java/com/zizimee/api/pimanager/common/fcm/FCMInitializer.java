package com.zizimee.api.pimanager.common.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FCMInitializer {

    private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);
    private static final String firebaseConfigPath = "firebase/firebase_service_key.json";


    //ADC 사용한 사용자 인증 정보 확인
    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options, "PI-Manager");
                logger.info("Firebase application has been initialized");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
