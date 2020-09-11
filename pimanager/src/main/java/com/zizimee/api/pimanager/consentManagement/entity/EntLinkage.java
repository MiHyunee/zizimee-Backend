package com.zizimee.api.pimanager.consentManagement.entity;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Builder
    private EntLinkage(Enterprise enterprise, User user) {
        this.enterprise = enterprise;
        this.user = user;
    }
}
