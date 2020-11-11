package com.zizimee.api.pimanager.common.fcm;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RequestMapping("/fcm")
@RestController
public class FCMController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FCMService fcmService;

    //전체 인원한테
    @GetMapping("/send")
    public ResponseEntity sendNotification() {
        String notifications = FCMNotice.MessageJson();

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = fcmService.sendNotificationToAll(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("FCM ERROR");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
/*
    //특정 인원한테
    public ResponseEntity sendDone() {

    }

 */
}