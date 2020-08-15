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
    private Long id_enterprise;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String domain_address;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Builder
    public Enterprise(String name, String domain_address, String id, String password){
        this.name = name;
        this.domain_address = domain_address;
        this.id = id;
        this.password = password;
    }
}
