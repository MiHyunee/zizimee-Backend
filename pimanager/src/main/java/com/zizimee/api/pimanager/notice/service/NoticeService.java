package com.zizimee.api.pimanager.notice.service;

import com.zizimee.api.pimanager.notice.dto.NoticeListResponseDto;
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
        return noticeRepository.save(requestDto.toEntity()).getId_notice();
    }

    @Transactional
    public Long update(Long id_notice, NoticeUpdateRequestDto requestDto ){
        Notice notice = noticeRepository.findById(id_notice)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id_notice));
        notice.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getType());
        return id_notice;
    }

    @Transactional
    public void delete (Long id_notice){
        Notice notice = noticeRepository.findById(id_notice)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id_notice));
        noticeRepository.delete(notice);
    }

    @Transactional(readOnly=true)
    public NoticeResponseDto findById(Long id_notice){
        Notice entity = noticeRepository.findById(id_notice)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id_notice));
        return new NoticeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> findAllDesc() {
        return noticeRepository.findAllDesc().stream()
                .map(NoticeListResponseDto::new)
                .collect(Collectors.toList());
    }
}
