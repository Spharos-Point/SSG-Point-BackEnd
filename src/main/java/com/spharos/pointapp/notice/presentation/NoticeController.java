package com.spharos.pointapp.notice.presentation;

import com.spharos.pointapp.notice.application.NoticeService;
import com.spharos.pointapp.notice.domain.NoticeList;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.vo.NoticeCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class NoticeController {
    private final NoticeService noticeService;
// 공지사항 생성
    @PostMapping("/notice")
    public void createNotice(@RequestBody NoticeCreate noticeCreate) {
        NoticeCreateDto noticeCreateDto = NoticeCreateDto.builder()
                .title(noticeCreate.getTitle())
                .context(noticeCreate.getContext())
                .adminId(noticeCreate.getAdminId())
                .build();
        noticeService.createNotice(noticeCreateDto);
    }
    //공지사항 삭제
    @DeleteMapping("/notice")
    private void deleteNotice(@RequestParam(name = "noticeId", defaultValue = "") Long noticeId) {
        noticeService.deleteNotice(noticeId);
    }

    @GetMapping("/notice")
    private List<NoticeList> getNotice() {
        return noticeService.getNotice();
    }


}

//        log.info("{}", noticeCreate);
//        ModelMapper mapper = new ModelMapper();
//        NoticeCreateDto noticeCreateDto = mapper.map(noticeCreate, NoticeCreateDto.class);
//        noticeService.writeNotice(noticeCreateDto);

