package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.notice.domain.Notice;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.dto.NoticeGetDto;
import com.spharos.pointapp.notice.infrastructure.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService {
    private final NoticeRepository noticeRepository;


    @Override
    public void createNotice(NoticeCreateDto noticeCreateDto) {
        noticeRepository.save(
                Notice.builder()
                        .title(noticeCreateDto.getTitle())
                        .context(noticeCreateDto.getContext())
                        .build());
    }

    @Override
    public List<NoticeGetDto> getNotices() {
        List<Notice> noticeList = noticeRepository.findAll();
        log.info("{}", noticeList);
        List<NoticeGetDto> noticeGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        noticeList.forEach(
                notice -> noticeGetDtoList.add(
                        mapper.map(notice, NoticeGetDto.class)
                )
        );

        log.info("{}", noticeGetDtoList);
        return noticeGetDtoList;
    }

    @Override
    public void deleteNotice(Long noticeId) { noticeRepository.deleteById(noticeId); }

}




