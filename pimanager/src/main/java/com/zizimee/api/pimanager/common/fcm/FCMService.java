package com.zizimee.api.pimanager.common.fcm;

import com.zizimee.api.pimanager.user.entity.User;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Component
@RequiredArgsConstructor
public class FCMService {

    private static final Logger logger = LoggerFactory.getLogger(FCMService.class);
    private static final String firebase_server_key = "AAAAwQ0JbNk:APA91bEQH_FGku_2E0FB_OepCEPIgPafJnj9RYeIHikpwH6omcoksORSf795KZ5G1wWaEDMUycHWMot9oCL_skRhfBiQUUHIZyQhnDCRIBUTHXTU6uiWKOXYnJ3ExRqgKRMlLf6X4Irh";
    private final UserRepository userRepository;

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/pi-manager-285818/messages:send";

    public void updateFcmToken(User user, String token) {
        user.updateFcmToken(token);
    }

    @Async
    public CompletableFuture<String> sendNotificationToAll(HttpEntity<String> request) {

        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new FCMHeaderRequestInterceptor("Authorization", "key=" + firebase_server_key));
        interceptors.add(new FCMHeaderRequestInterceptor("Content-Type", "application/json; UTF-8"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(API_URL, request, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}