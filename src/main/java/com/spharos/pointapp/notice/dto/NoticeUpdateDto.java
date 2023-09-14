package com.spharos.pointapp.notice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoticeUpdateDto {
    private String title;
    private String context;
    private Long noticeId;
}
