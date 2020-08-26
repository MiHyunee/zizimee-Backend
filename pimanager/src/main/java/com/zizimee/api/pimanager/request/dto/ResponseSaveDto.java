package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.Response.Progress;
import jdk.vm.ci.meta.Local;
import lombok.Builder;

import java.time.LocalDate;

public class ResponseSaveDto{
    private Progress progress;
    private String content;
    private LocalDate doneDate;

    @Builder
    public ResponseSaveDto(){
        this.progress = Progress.InProgress;
        this.content = "";
        this.doneDate = null;
    }

    public Response toEntity(){
        return Response.builder()
                .progress(progress)
                .content(content)
                .doneDate(doneDate)
                .build();
    }
}
