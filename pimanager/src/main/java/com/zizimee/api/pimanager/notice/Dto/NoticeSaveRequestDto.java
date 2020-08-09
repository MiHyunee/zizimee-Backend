package com.zizimee.api.pimanager.notice.Dto;

import com.zizimee.api.pimanager.notice.Entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    private String title;
    private String content;
    private String type;
    
    @Builder
    public NoticeSaveRequestDto(String title, String content, String type){
        this.title = title;
        this.content = content;
        this.type = type;
    }
    
    public Notice toEntity(){
        return Notice.builder()
                .title(title)
                .content(content)
                .type(type)
                .build();
    }
}
