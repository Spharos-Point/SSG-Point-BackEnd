package com.spharos.pointapp.event.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventListRes {
    private Long id;
    private String eventName;
    private String regDate;
    private String endDate;
    private String eventDesc;
    private String eventType;
    private String prizeType;
    private String img;
    private Boolean expired;
}
