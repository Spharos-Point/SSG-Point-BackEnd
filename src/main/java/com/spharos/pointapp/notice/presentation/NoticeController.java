package com.spharos.pointapp.notice.presentation;

import com.spharos.pointapp.notice.application.NoticeService;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.dto.NoticeGetDto;
import com.spharos.pointapp.notice.vo.NoticeCreate;
import com.spharos.pointapp.notice.vo.NoticeGetOut;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록합니다.", tags = { "Notice Controller" })
    @PostMapping("/notice")
    public void createNotice(@RequestBody NoticeCreate noticeCreate) {
        NoticeCreateDto noticeCreateDto = NoticeCreateDto.builder()
                .title(noticeCreate.getTitle())
                .context(noticeCreate.getContext())
                .build();
        noticeService.createNotice(noticeCreateDto);
    }

    // notice 조회
    @Operation(summary = "공지사항 조회", description = "공지사항을 조회합니다.", tags = { "Notice Controller" })
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
    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.", tags = { "Notice Controller" })
    @DeleteMapping("/notice")
    private void deleteNotice(@RequestParam(name = "noticeId", defaultValue = "") Long noticeId) {
        noticeService.deleteNotice(noticeId);
    }
}


