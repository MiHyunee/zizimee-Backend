package com.zizimee.api.pimanager.notice.service;

import com.zizimee.api.pimanager.notice.dto.NoticeResponseDto;
import com.zizimee.api.pimanager.notice.dto.NoticeSaveRequestDto;
import com.zizimee.api.pimanager.notice.dto.NoticeUpdateRequestDto;
import com.zizimee.api.pimanager.notice.entity.Notice;
import com.zizimee.api.pimanager.notice.entity.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequestDto requestDto){

        return noticeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void update(Long id, NoticeUpdateRequestDto requestDto ){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        notice.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public void delete (Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        noticeRepository.delete(notice);
    }

    @Transactional(readOnly=true)
    public NoticeResponseDto findById(Long id){
        Notice entity = noticeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new NoticeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> findAllDesc() {
        return noticeRepository.findAllDesc().stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList());
    }
}
