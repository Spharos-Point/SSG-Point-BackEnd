package com.spharos.pointapp.notice.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class NoticeGetOut {
    private String title;
    private String context;
    private Long noticeId;
}
