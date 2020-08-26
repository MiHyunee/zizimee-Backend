package com.zizimee.api.pimanager.request.service;

import com.zizimee.api.pimanager.request.dto.RequestResponseDto;
import com.zizimee.api.pimanager.request.dto.ResponseDto;
import com.zizimee.api.pimanager.request.dto.ResponseSaveDto;
import com.zizimee.api.pimanager.request.dto.ResponseUpdateDto;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResponseService {
    private final ResponseRepository responseRepository;

    @Transactional
    public void save(ResponseSaveDto requestDto){
        responseRepository.save(requestDto.toEntity());
    }

    @Transactional
    public void update(Long id, ResponseUpdateDto requestDto ){
        Response response = responseRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        response.update(requestDto.getProgress(), requestDto.getContent());
    }

    @Transactional
    public void delete (Long id){
        Response response = responseRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        responseRepository.delete(response);
    }

    @Transactional(readOnly=true)
    public ResponseDto findById(Long id){
        Response response = responseRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new ResponseDto(response);
    }

    @Transactional(readOnly = true)
    public List<ResponseDto> findAllDesc() {
        return responseRepository.findAllDesc().stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }

}
