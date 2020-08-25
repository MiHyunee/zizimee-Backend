package com.zizimee.api.pimanager.notice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {
    private String title;
    private String content;
    private String type;

    @Builder
    public NoticeUpdateRequestDto(String title, String content, String type){
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
