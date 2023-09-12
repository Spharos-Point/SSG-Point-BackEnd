package com.spharos.pointapp.notice.dto;

import lombok.*;

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
