package com.zizimee.api.pimanager.request.service;

import com.zizimee.api.pimanager.request.dto.RequestResponseDto;
import com.zizimee.api.pimanager.request.dto.RequestSaveDto;
import com.zizimee.api.pimanager.request.dto.RequestUpdateDto;
import com.zizimee.api.pimanager.request.dto.ResponseDto;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RequestService {
    private final RequestRepository requestRepository;

    @Transactional
    public Long save(RequestSaveDto requestDto){

        return requestRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public void update(Long id, RequestUpdateDto requestDto ){
        Request request = requestRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        request.update(requestDto.getType(), requestDto.getContent(), requestDto.getRequestDate());
    }

    @Transactional
    public void delete (Long id){
        Request request = requestRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        requestRepository.delete(request);
    }

    @Transactional(readOnly=true)
    public RequestResponseDto findById(Long id){
        Request request = requestRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new RequestResponseDto(request);
    }

    @Transactional(readOnly = true)
    public List<RequestResponseDto> findAllDesc() {
        return requestRepository.findAllDesc().stream()
                .map(RequestResponseDto::new)
                .collect(Collectors.toList());
    }
}
