package com.zizimee.api.pimanager.report.entity;

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
    private Long id_report;

    @ManyToOne
    @JoinColumn(name = "id_enterprise")
    private Integer id_enterprise;

    @Column(nullable = false)
    private String image_url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end_date;

    @Builder
    public Report(String image_url, Date start_date, Date end_date){
        this.image_url = image_url;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
