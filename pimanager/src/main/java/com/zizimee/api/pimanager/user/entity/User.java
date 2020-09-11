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

    private String uid;

    private String name;

    private String profileImg;


    @Builder
    public User(String uid, String name, String profileImg) {
        this.uid = uid;
        this.name = name;
        this.profileImg = profileImg;
    }
}
