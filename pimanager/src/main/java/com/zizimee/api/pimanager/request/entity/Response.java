package com.zizimee.api.pimanager.request.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Response{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate doneDate;

    @OneToOne
    @JoinColumn(name = "id_request")
    private Request request;

    @Builder
    public Response(Request request, String content){
        this.doneDate = LocalDate.now();
        this.request = request;
        this.content = content;
    }
}
