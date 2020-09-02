package com.zizimee.api.pimanager.log.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name="ENTERPRISE_ID")
    private Enterprise enterpriseId;

    @Column
    private String intend;

    @Lob
    private String providedInfo;

    @Column
    private String thirdParty;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate useDate;

    @Builder
    public Log(User userId, Enterprise enterpriseId, String intend, String providedInfo, String thirdParty, LocalDate useDate){
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.intend = intend;
        this.providedInfo = providedInfo;
        this.thirdParty = thirdParty;
        this.useDate = useDate;
    }

    public void update(User userId,String intend, String providedInfo, String thirdParty, LocalDate useDate){
        this.userId = userId;
        this.intend = intend;
        this.providedInfo = providedInfo;
        this.thirdParty = thirdParty;
        this.useDate = useDate;
    }

    }
