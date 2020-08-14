package com.zizimee.api.pimanager.notice.Service;

import com.zizimee.api.pimanager.notice.Dto.NoticeResponseDto;
import com.zizimee.api.pimanager.notice.Dto.NoticeSaveRequestDto;
import com.zizimee.api.pimanager.notice.Dto.NoticeUpdateRequestDto;
import com.zizimee.api.pimanager.notice.Entity.Notice;
import com.zizimee.api.pimanager.notice.Entity.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
