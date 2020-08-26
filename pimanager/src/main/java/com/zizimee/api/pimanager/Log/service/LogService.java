package com.zizimee.api.pimanager.Log.service;

import com.zizimee.api.pimanager.Log.dto.LogSaveDto;
import com.zizimee.api.pimanager.Log.entity.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository logRepository;

    @Transactional
    public Long save(LogSaveDto requestDto){
        return logRepository.save(requestDto.toEntity()).getId();
    }

}
