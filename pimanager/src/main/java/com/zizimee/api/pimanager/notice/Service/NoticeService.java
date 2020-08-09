package com.zizimee.api.pimanager.notice.Service;

import com.zizimee.api.pimanager.notice.Dto.NoticeResponseDto;
import com.zizimee.api.pimanager.notice.Dto.NoticeSaveRequestDto;
import com.zizimee.api.pimanager.notice.Dto.NoticeUpdateRequestDto;
import com.zizimee.api.pimanager.notice.Entity.Notice;
import com.zizimee.api.pimanager.notice.Entity.NoticeRepository;
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
    public Long update(Long id, NoticeUpdateRequestDto requestDto ){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        notice.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getType());
        return id;
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
}
