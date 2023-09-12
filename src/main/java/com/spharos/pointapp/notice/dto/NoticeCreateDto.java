package com.spharos.pointapp.notice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateDto {
    private String title;
    private String context;
    private Long noticeId;
    private LocalDateTime createAt;
}
