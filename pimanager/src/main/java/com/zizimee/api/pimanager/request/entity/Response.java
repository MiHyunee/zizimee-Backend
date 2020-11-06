package com.zizimee.api.pimanager.request.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @Lob
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate doneDate;

    @Builder
    public Response(Progress progress, String content, LocalDate doneDate){
        this.progress = progress;
        this.content = content;
        this.doneDate = doneDate;
    }

    public void update(Progress progress, String content, LocalDate doneDate){
        this.progress = progress;
        this.content = content;
        this.doneDate = doneDate;
    }
}
