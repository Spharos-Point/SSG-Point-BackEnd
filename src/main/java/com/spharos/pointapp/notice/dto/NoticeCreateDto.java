package com.spharos.pointapp.notice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateDto {
    private String title;
    private String context;
    private String adminId;
}
