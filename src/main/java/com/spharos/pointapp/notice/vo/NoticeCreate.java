package com.spharos.pointapp.notice.vo;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreate {
    private String title;
    private String context;
    private Long noticeId;
    private LocalDateTime createAt;
}