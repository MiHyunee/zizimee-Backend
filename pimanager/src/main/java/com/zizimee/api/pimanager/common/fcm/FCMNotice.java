package com.zizimee.api.pimanager.common.fcm;

import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class FCMNotice {

    private static UserRepository userRepository;

    private static EnterpriseRepository enterpriseRepository;

    public static String MessageJson() {
        JSONObject body = new JSONObject();

        List<String> tokenList = enterpriseRepository.getFcmTokens();
        System.out.println(tokenList.get(0));
        JSONArray tokenJsonList = new JSONArray();
        tokenJsonList.addAll(tokenList);

        body.put("registration_ids", tokenJsonList);

        JSONObject message = new JSONObject();
        message.put("title", "[PI-MANAGER]");
        message.put("body", "새로운 공지가 추가 되었습니다.");

        body.put("notification", message);

        System.out.println(body.toString());

        return body.toString();
    }
}
