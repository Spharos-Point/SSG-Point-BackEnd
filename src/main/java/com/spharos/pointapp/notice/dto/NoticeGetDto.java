package com.spharos.pointapp.notice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeGetDto {
    private String title;
    private String context;
    private Long noticeId;
}
