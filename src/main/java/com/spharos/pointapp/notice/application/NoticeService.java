package com.spharos.pointapp.notice.application;

import com.spharos.pointapp.notice.dto.NoticeCreateDto;

public interface NoticeService {
    void createNotice(NoticeCreateDto noticeCreateDto);
}
