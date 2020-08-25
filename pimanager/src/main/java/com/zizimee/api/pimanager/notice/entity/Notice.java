package com.zizimee.api.pimanager.notice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(nullable = false)
    private String type;

    @Builder
    public Notice(String title, String content, String type){
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public void update(String title, String content, String type){
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
