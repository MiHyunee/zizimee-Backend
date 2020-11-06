package com.zizimee.api.pimanager.request.service;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.request.dto.RequestResponseDto;
import com.zizimee.api.pimanager.request.dto.RequestSaveDto;
import com.zizimee.api.pimanager.request.dto.RequestUpdateDto;
import com.zizimee.api.pimanager.request.dto.ResponseDto;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.RequestRepository;
import com.zizimee.api.pimanager.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final EnterpriseRepository enterpriseRepository;

    @Transactional
    public Long save(RequestSaveDto requestDto, User user){
        return requestRepository.save(requestDto.toEntity(user)).getId();
    }
    @Transactional
    public void update(Long id, RequestUpdateDto requestDto ){
        Request request = requestRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        Enterprise entId = enterpriseRepository.findByName(requestDto.getName());
        request.update(requestDto.getContent(), requestDto.getStartDate(),requestDto.getEndDate(), entId);
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
