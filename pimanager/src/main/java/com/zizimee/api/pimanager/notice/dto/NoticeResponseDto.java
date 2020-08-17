package com.zizimee.api.pimanager.notice.dto;

import com.zizimee.api.pimanager.notice.entity.Notice;
import lombok.Getter;

@Getter
public class NoticeResponseDto {
    private Long notice_id;
    private String title;
    private String content;
    private String type;

    public NoticeResponseDto(Notice entity){
        this.notice_id = entity.getId_notice();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.type = entity.getType();
    }
}
