package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.Progress;
import lombok.Getter;
import lombok.Builder;

import java.time.LocalDate;

@Getter
public class ResponseDto{
    private Long id;
    private Progress progress;
    private String content;
    private LocalDate doneDate;

    public ResponseDto(Response entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.progress = entity.getProgress();
        this.doneDate = entity.getDoneDate();
    }

    @Builder
    public ResponseDto(Long id){

        this.id = id;
    }

}
