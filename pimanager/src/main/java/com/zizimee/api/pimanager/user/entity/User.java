package com.zizimee.api.pimanager.user.entity;

import lombok.Getter;

@Getter
public class User {

    private Long id;

    private String uid;

    private ProviderType provider;

    private String email;
}
