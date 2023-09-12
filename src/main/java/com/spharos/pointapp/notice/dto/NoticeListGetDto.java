package com.spharos.pointapp.notice.dto;

import lombok.*;

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
}
