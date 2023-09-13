package com.spharos.pointapp.notice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListGetDto {
    private String title;
    private String context;
    private Long noticeId;
    private LocalDateTime createAt;
}
