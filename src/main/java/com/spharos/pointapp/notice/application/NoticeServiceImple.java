package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.notice.domain.Notice;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.infrastructure.NoticeListRepository;
import com.spharos.pointapp.notice.infrastructure.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService{
    private final NoticeRepository noticeRepository;
    private final NoticeListRepository noticeListRepository;

// 공지사항 생성
    @Override
    public void createNotice(NoticeCreateDto noticeCreateDto) {
        noticeRepository.save(
                Notice.builder()
                        .title(noticeCreateDto.getTitle())
                        .context(noticeCreateDto.getContext())
                        .build());
    }

    // 공지사항 삭제
    @Override
    public void deleteNotice(Long noticeId) { noticeRepository.deleteById(noticeId);}
    }

