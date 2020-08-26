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
    @JoinColumn(name = "USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "ENTERPRISE_ID")
    private Enterprise enterpriseId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    private RequestType type;

    @Lob
    private String content;

    @Builder
    public Request(User userId, Enterprise enterpriseId, LocalDate requestDate, RequestType type, String content){
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.requestDate = requestDate;
        this.type = type;
        this.content = content;
    }

    public void update(RequestType type, String content, LocalDate requestDate){
        this.requestDate = requestDate;
        this.type = type;
        this.content = content;
    }

}
