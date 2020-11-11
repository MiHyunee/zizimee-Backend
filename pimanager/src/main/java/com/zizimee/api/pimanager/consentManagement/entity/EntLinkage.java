package com.zizimee.api.pimanager.consentManagement.entity;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EntLinkage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterpriseId;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User userId;

    @Column
    private String uid;

}
