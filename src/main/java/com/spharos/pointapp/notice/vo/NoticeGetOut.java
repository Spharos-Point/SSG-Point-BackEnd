package com.spharos.pointapp.notice.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoticeGetOut {
    private String title;
    private String context;
    private Long noticeId;
}
