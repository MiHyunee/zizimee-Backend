package com.zizimee.api.pimanager.request.dto;

import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.Response;
import lombok.Getter;
import lombok.Builder;

@Getter
public class RequestResponseDto {

    Request request;
    Response response;

    @Builder
    public RequestResponseDto(Request request, Response response){
        this.request = request;
        this.response = response;
    }

}
