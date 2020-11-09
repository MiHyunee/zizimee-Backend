package com.zizimee.api.pimanager.log.entity;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ConsentForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Enterprise_id")
    private Enterprise enterpriseId;

    @Lob
    private String consentItem;

    @Column
    private LocalDate date;

    @Builder
    public ConsentForm (Long id, Enterprise enterpriseId, String consentItem, LocalDate date){
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.consentItem = consentItem;
        this.date = date;
    }
    public void update (String consentItem) {
        this.consentItem = consentItem;
    }
}
