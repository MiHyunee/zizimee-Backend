package com.zizimee.api.pimanager.notice.Controller;

import com.zizimee.api.pimanager.notice.Dto.NoticeResponseDto;
import com.zizimee.api.pimanager.notice.Dto.NoticeSaveRequestDto;
import com.zizimee.api.pimanager.notice.Dto.NoticeUpdateRequestDto;
import com.zizimee.api.pimanager.notice.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @PutMapping("/notice")
    public Long save(@RequestBody NoticeSaveRequestDto requestDto){
        return noticeService.save(requestDto);
    }

    @PutMapping("/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateRequestDto requestDto){
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
}
