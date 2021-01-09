package com.zizimee.api.pimanager.enterprise.entity;

import com.zizimee.api.pimanager.report.entity.Report;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false)
    private String salt;

    private String domainAddress;
    private String registerNmb;
    private String fcmToken;

    @OneToMany(mappedBy="enterprise")
    private List<Report> reports;


    @Builder
    public Enterprise(String name, String signUpId, String password, String domainAddress, String registerNmb, String fcmToken) {
        this.name = name;
        this.signUpId = signUpId;
        this.password = password;
        this.domainAddress = domainAddress;
        this.registerNmb = registerNmb;
        this.fcmToken = fcmToken;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
