package com.zizimee.api.pimanager.notice.Dto;

import com.zizimee.api.pimanager.notice.Entity.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListResponseDto {
    private Long id_notice;
    private String title;
    private String content;
    private String type;
    private LocalDateTime update_date;

    public NoticeListResponseDto(Notice entity) {
        this.id_notice = entity.getId_notice();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.type = entity.getType();
        this.update_date = entity.getUpdate_Date();
    }
}
