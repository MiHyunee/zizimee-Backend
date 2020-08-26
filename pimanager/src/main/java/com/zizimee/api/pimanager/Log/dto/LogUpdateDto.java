package com.zizimee.api.pimanager.Log.dto;

import com.zizimee.api.pimanager.user.entity.User;
import lombok.Getter;

@Getter
public class LogUpdateDto {
    private User userId;
    private String intend;
    private String providedInfo;
    private String thirdParty;
}
