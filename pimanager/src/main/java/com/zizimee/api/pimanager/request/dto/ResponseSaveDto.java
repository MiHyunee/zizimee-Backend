package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.BaseTimeEntity;
import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.Response.Progress;
import lombok.Builder;

import java.time.LocalDate;

public class ResponseSaveDto {
    private Progress progress;
    private String content;

    @Builder
    public ResponseSaveDto(){
        this.progress = Progress.InProgress;
        this.content = "";
    }

    public Response toEntity(){
        return Response.builder()
                .progress(progress)
                .content(content)
                .build();
    }
}
