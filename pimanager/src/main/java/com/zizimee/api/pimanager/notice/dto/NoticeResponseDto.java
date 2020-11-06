package com.zizimee.api.pimanager.notice.dto;

import com.zizimee.api.pimanager.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate updateDate;

    public NoticeResponseDto(Notice entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.updateDate = entity.getUpdateDate();
    }

    @Builder
    public NoticeResponseDto(Long id) {
        this.id = id;
    }
}
