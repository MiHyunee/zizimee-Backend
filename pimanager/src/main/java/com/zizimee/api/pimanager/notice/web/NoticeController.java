package com.zizimee.api.pimanager.notice.web;

import com.zizimee.api.pimanager.notice.dto.NoticeResponseDto;
import com.zizimee.api.pimanager.notice.dto.NoticeSaveRequestDto;
import com.zizimee.api.pimanager.notice.dto.NoticeUpdateRequestDto;
import com.zizimee.api.pimanager.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/notice")
    public ResponseEntity save(@RequestBody NoticeSaveRequestDto requestDto) {
        Long id = noticeService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(NoticeResponseDto.builder()
                        .id(id)
                        .build());
    }

    @PutMapping("/notice/{id}")
    public ResponseEntity update(@PathVariable Long id,
                       @RequestBody NoticeUpdateRequestDto requestDto){

        noticeService.update(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(NoticeResponseDto.builder()
                .id(id)
                .build());
    }

    @DeleteMapping("/notice/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        noticeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/notice/{id}")
    public ResponseEntity<NoticeResponseDto> findById(@PathVariable Long id){
        NoticeResponseDto dto = noticeService.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/notice")
    public ResponseEntity<List<NoticeResponseDto>> findAll() {
        List<NoticeResponseDto> noticeList = noticeService.findAllDesc();

        return ResponseEntity.status(HttpStatus.OK)
                .body(noticeList);
    }
}
