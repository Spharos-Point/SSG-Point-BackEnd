package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.notice.domain.NoticeList;
import com.spharos.pointapp.notice.dto.NoticeCreateDto;

import java.util.List;

public interface NoticeService {
    //공지사항 생성
    void createNotice(NoticeCreateDto noticeCreateDto);

    //공지사항 삭제
    void deleteNotice(Long noticeId);

    List<NoticeList> getNotice();
}
