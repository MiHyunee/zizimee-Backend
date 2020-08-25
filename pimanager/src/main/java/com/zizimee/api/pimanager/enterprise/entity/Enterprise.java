package com.zizimee.api.pimanager.Enterprise.Entity;


import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnterprise;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String domainAddress;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Builder
    public Enterprise(String name, String domainAddress, String id, String password){
        this.name = name;
        this.domainAddress = domainAddress;
        this.id = id;
        this.password = password;
    }
}
