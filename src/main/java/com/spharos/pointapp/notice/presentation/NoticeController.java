package com.spharos.pointapp.notice.presentation;

import com.spharos.pointapp.notice.application.NoticeService;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.dto.NoticeGetDto;
import com.spharos.pointapp.notice.vo.NoticeCreate;
import com.spharos.pointapp.notice.vo.NoticeGetOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/notice")
    public void createNotice(@RequestBody NoticeCreate noticeCreate) {
        NoticeCreateDto noticeCreateDto = NoticeCreateDto.builder()
                .title(noticeCreate.getTitle())
                .context(noticeCreate.getContext())
                .createAt(noticeCreate.getCreateAt())
                .build();
        noticeService.createNotice(noticeCreateDto);
    }

    // notice 조회
    @GetMapping("/notice")
    public List<NoticeGetOut> getAllNotices() {
        ModelMapper mapper = new ModelMapper();
        List<NoticeGetDto> noticeGetDtoList = noticeService.getNotices();
        log.info("{}", noticeGetDtoList);
        List<NoticeGetOut> noticeGetOutList = new ArrayList<>();
        noticeGetDtoList.forEach(
                noticeGetDto -> noticeGetOutList.add(
                        mapper.map(noticeGetDto, NoticeGetOut.class)
                )
        );
        return noticeGetOutList;
    }

    // notice 삭제
    @DeleteMapping("/notice")
    private void deleteNotice(@RequestParam(name = "noticeId", defaultValue = "") Long noticeId) {
        noticeService.deleteNotice(noticeId);
    }
}


