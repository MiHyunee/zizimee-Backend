package com.zizimee.api.pimanager.report.entity;

import com.zizimee.api.pimanager.Enterprise.Entity.Enterprise;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReport;

    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise idEnterprise;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Builder
    public Report(String imageUrl, Date startDate, Date endDate){
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
