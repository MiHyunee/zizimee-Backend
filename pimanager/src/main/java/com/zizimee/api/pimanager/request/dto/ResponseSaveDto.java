package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.Progress;

import java.time.LocalDate;

public class ResponseSaveDto{
    private Progress progress = Progress.InProgress;
    private String content = "";
    private LocalDate doneDate = null;

    public Response toEntity(){
        return Response.builder()
                .progress(progress)
                .content(content)
                .doneDate(doneDate)
                .build();
    }


}
