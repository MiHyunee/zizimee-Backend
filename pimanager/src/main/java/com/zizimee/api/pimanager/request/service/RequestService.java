package com.zizimee.api.pimanager.request.service;

import com.zizimee.api.pimanager.common.jwt.JwtTokenProvider;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.request.dto.RequestResponseDto;
import com.zizimee.api.pimanager.request.dto.RequestSaveDto;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.RequestRepository;
import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.ResponseRepository;
import com.zizimee.api.pimanager.user.entity.User;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RequestService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RequestRepository requestRepository;
    private final ResponseService responseService;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;

    @Transactional
    public void save(RequestSaveDto requestDto, String token) throws Throwable {
        User user = userRepository.findByUid(jwtTokenProvider.getUserId(token))
                .orElseThrow(()-> new IllegalArgumentException("token의 userId가 없습니다."));
        Enterprise enterprise = enterpriseRepository.findById(requestDto.getEnterpriseId())
                .orElseThrow(()-> new IllegalArgumentException("기업이 없습니다"));
        Request request = requestRepository.save(requestDto.toEntity(user, enterprise));
        responseService.save(request);
    }

    @Transactional(readOnly = true)
    public List<RequestResponseDto> findAllRequestResponseDesc(String token) {
        List<RequestResponseDto> requestResponseDtoList = new ArrayList<>();
        Request request;
        Response response;

        User user = userRepository.findByUid(jwtTokenProvider.getUserId(token))
                .orElseThrow(()-> new IllegalArgumentException("token의 userId가 없습니다."));
        Long userId = user.getId();
        List<Request> requestList = requestRepository.findAllDescByUser(userId);
        for(int i=0; i<requestList.size(); i++) {
            request = requestList.get(i);
            response = responseService.findByRequestId(request.getId());
            requestResponseDtoList.add(RequestResponseDto.builder().
                    request(request)
                    .response(response)
                    .build());
        }
        return requestResponseDtoList;
    }

}
