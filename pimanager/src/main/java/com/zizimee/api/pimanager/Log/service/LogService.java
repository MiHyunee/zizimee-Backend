package com.zizimee.api.pimanager.Log.service;

import com.zizimee.api.pimanager.Log.dto.LogSaveDto;
import com.zizimee.api.pimanager.Log.dto.LogUpdateDto;
import com.zizimee.api.pimanager.Log.entity.Log;
import com.zizimee.api.pimanager.Log.entity.LogRepository;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.user.entity.User;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository logRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(LogSaveDto requestDto){

        User userId = userRepository.findByName(requestDto.getName());
        return logRepository.save(requestDto.toEntity(userId)).getId();
    }

    @Transactional
    public void update(Long id, LogUpdateDto requestDto ){
         Log log = logRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        User userId = userRepository.findByName(requestDto.getName());
        log.update(userId, requestDto.getIntend(), requestDto.getProvidedInfo(),
                requestDto.getThirdParty(), requestDto.getUseDate());
    }

}
