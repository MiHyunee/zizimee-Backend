package com.zizimee.api.pimanager.request.entity;

import com.zizimee.api.pimanager.request.entity.BaseTimeEntity;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Response extends BaseTimeEntity {
    public enum Progress{
        InProgress, DONE, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @Lob
    private String content;

    @Builder
    public Response(Progress progress, String content){
        this.progress = progress;
        this.content = content;
    }

    public void update(Progress progress, String content ){
        this.progress = progress;
        this.content = content;
    }
}
