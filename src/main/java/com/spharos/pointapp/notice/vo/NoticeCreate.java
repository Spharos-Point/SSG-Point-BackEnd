package com.spharos.pointapp.notice.vo;


import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreate {
    private String title;
    private String context;
}