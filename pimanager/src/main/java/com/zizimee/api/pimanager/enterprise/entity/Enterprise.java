package com.zizimee.api.pimanager.enterprise.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String signUpId;

    @Column(nullable = false)
    private String password;

    private String domainAddress;
    private String registerNmb;

    @Builder
    public Enterprise(String name, String signUpId, String password, String domainAddress, String registerNmb, String profileImg) {
        this.name = name;
        this.signUpId = signUpId;
        this.password = password;
        this.domainAddress = domainAddress;
        this.registerNmb = registerNmb;
    }


}