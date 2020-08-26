package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.request.entity.Response.Progress;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseUpdateDto {
    private Progress progress;
    private String content;

    @Builder
    public ResponseUpdateDto(Progress progress, String content){
        this.progress = progress;
        this.content = content;
    }
}
