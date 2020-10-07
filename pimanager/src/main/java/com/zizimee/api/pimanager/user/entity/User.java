package com.zizimee.api.pimanager.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private ProviderType provider;

    private String uid;

    private String name;

    private String profileImg;

    private String fcmToken;

    @Builder
    public User(ProviderType provider, String uid, String name, String profileImg) {
        this.provider = provider;
        this.uid = uid;
        this.name = name;
        this.profileImg = profileImg;
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
