package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.admin.administrator.infrastructure.AdministratorRepository;
import com.spharos.pointapp.notice.domain.Notice;
import com.spharos.pointapp.notice.domain.NoticeList;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;
import com.spharos.pointapp.notice.infrastructure.NoticeListRepository;
import com.spharos.pointapp.notice.infrastructure.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService{
    private final NoticeRepository noticeRepository;
    private final NoticeListRepository noticeListRepository;
    private final AdministratorRepository administratorRepository;

// 공지사항 생성
    @Override
    public void createNotice(NoticeCreateDto noticeCreateDto) {

        Administrator admin = administratorRepository.findByLoginId(noticeCreateDto.getAdminId())
                .orElseThrow(()->new IllegalArgumentException("Not found Admin"));

        noticeListRepository.save(
                NoticeList.builder()
                        .notice(noticeRepository.save(
                                Notice.builder()
                                        .title(noticeCreateDto.getTitle())
                                        .context(noticeCreateDto.getContext())
                                        .build()))
                        .canView(true)
                        .administrator(admin)
                        .build()
        );
    }

    // 공지사항 삭제
    @Override
    public void deleteNotice(Long noticeId) {
        NoticeList getNoticeList = noticeListRepository.findByNoticeId(noticeId)
                .orElseThrow(()->new IllegalArgumentException("Not found Notice"));
        noticeListRepository.save(NoticeList.builder()
                .Id(getNoticeList.getId())
                .notice(getNoticeList.getNotice())
                .canView(false)
                .administrator(getNoticeList.getAdministrator())
                .build());
//        noticeListRepository.delete(noticeList);
//        noticeRepository.deleteById(noticeId);
    }

    @Override
    public List<NoticeList> getNotice() {
        List<NoticeList> noticeList = noticeListRepository.findAllByCanViewTrue();

        return noticeList;
    }

}

