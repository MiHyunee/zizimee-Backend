package com.zizimee.api.pimanager.request.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Request {

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
    private LocalDate requestDate; //basetime entity를 받아온다면 이름을 바꿀수 있는지 확인

    @Lob
    private String type;

    @Column(length = 500)
    private String content;

    @Builder
    public Request(User userId, Enterprise enterpriseId, LocalDate requestDate, String type, String content){
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.requestDate = requestDate;
        this.type = type;
        this.content = content;
    }

    public void update(Enterprise enterpriseId, LocalDate requestDate, String type, String content){
        this.enterpriseId = enterpriseId;
        this.requestDate = requestDate;
        this.type = type;
        this.content = content;
    }

}