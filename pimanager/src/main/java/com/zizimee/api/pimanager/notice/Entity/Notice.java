package com.zizimee.api.pimanager.notice.Entity;

import lombok.Builder;
import lombok.Getter;
//import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
//@NoArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notice;

    @Column(nullable = false) //length 정해줄건지 결정
    private String title;

    @Column(nullable = false)
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

    public Long getId() {
        return id_notice;
    }

    public void setId(Long id) {
        this.id_notice = id_notice;
    }
}
