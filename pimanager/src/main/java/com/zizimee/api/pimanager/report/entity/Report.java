package com.zizimee.api.pimanager.report.entity;

import com.zizimee.api.pimanager.Enterprise.Entity.Enterprise;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_enterprise")
    private Enterprise enterpriseId;

    @Column(nullable = false)
    private String imageUrl;

    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @Builder
    public Report(String imageUrl, LocalDate startDate, LocalDate endDate, Enterprise enterpriseId){
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enterpriseId = enterpriseId;
    }
}
