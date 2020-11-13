package com.zizimee.api.pimanager.log.entity;

import com.zizimee.api.pimanager.consentManagement.entity.EntLinkage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class ConsentStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_form")
    private ConsentForm formId;

    @ManyToOne
    @JoinColumn(name = "id_ent_linkage")
    private EntLinkage linkId;

    @Column
    private String isConsent;

    @Column
    private Long signId;

    @Column(length=260)
    private byte[] signature;

    @Column
    private LocalDate date;

    @Builder
    public ConsentStatus(Long id, ConsentForm formId, EntLinkage linkId, String isConsent, Long signId, byte[] signature){
        this.id = id;
        this.formId = formId;
        this.linkId = linkId;
        this.isConsent = isConsent;
        this.signId = signId;
        this.signature = signature;
        this.date = LocalDate.now();
    }
}
