package com.spharos.pointapp.notice.presentation;

import com.spharos.pointapp.notice.application.NoticeService;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.vo.NoticeCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .build();
        noticeService.createNotice(noticeCreateDto);
    }
}

//        log.info("{}", noticeCreate);
//        ModelMapper mapper = new ModelMapper();
//        NoticeCreateDto noticeCreateDto = mapper.map(noticeCreate, NoticeCreateDto.class);
//        noticeService.writeNotice(noticeCreateDto);

