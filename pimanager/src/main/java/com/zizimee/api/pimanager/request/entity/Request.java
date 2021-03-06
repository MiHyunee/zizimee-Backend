package com.zizimee.api.pimanager.request.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zizimee.api.pimanager.BaseTimeEntity;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Request extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "Enterprise_id")
    private Enterprise enterpriseId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private RequestType type;

    @Column(length = 500)
    private String content;

    @Builder
    public Request(User userId, Enterprise enterpriseId, LocalDate startDate, LocalDate endDate, RequestType type, String content){
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.content = content;
    }

    public void update(RequestType type, String content, LocalDate startDate, LocalDate endDate, Enterprise enterpriseId){
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.content = content;
        this.enterpriseId = enterpriseId;
    }

}
