package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.notice.domain.Notice;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.infrastructure.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService{
    private final NoticeRepository noticeRepository;


    @Override
    public void createNotice(NoticeCreateDto noticeCreateDto) {
        noticeRepository.save(
                Notice.builder()
                        .title(noticeCreateDto.getTitle())
                        .context(noticeCreateDto.getContext())
                        .build());
    }

    }
//        Administrator administrator = administratorRepository.findByLoginId(noticeCreateDto.getTitle());
//        Notice notice = new Notice();
//        if(administrator==null) {
//            log.error("Not found Admin");
//        } else {
//            notice = noticeRepository.save(
//                    Notice.builder()
//                            .title(notice.getTitle())
//                            .context(notice.getContext())
//                            .build()
//            );
//        }
//    }

