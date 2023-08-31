package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.notice.dto.NoticeCreateDto;
// 1. 공지사항 생성
// 2. 공지사항 조회**//
public interface NoticeService {
    // 공지사항 생성
    void createNotice(NoticeCreateDto noticeCreateDto);

    // 공지사항 조회
}
