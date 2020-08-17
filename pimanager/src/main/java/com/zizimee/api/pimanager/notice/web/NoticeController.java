package com.zizimee.api.pimanager.notice.web;

import com.zizimee.api.pimanager.notice.dto.NoticeListResponseDto;
import com.zizimee.api.pimanager.notice.dto.NoticeResponseDto;
import com.zizimee.api.pimanager.notice.dto.NoticeSaveRequestDto;
import com.zizimee.api.pimanager.notice.dto.NoticeUpdateRequestDto;
import com.zizimee.api.pimanager.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/notice")
    public Long save(@RequestBody NoticeSaveRequestDto requestDto)
    {

        return noticeService.save(requestDto);
    }

    @PutMapping("/notice/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody NoticeUpdateRequestDto requestDto){

        return noticeService.update(id, requestDto);
    }

    @DeleteMapping("/notice/{id}")
    public Long delete(@PathVariable Long id){
        noticeService.delete(id);
        return id;
    }

    @GetMapping("/notice/{id}")
    public NoticeResponseDto findById(@PathVariable Long id){

        return noticeService.findById(id);
    }

    @GetMapping("/notice")
    public List<NoticeListResponseDto> findAll() {

        return noticeService.findAllDesc();
    }
}
