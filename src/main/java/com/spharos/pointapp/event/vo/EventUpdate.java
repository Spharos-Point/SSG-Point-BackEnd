package com.spharos.pointapp.event.vo;

import lombok.*;

@Getter
@ToString
public class EventUpdate {
    private Long eventId;
    private String eventName;
    private String regDate;
    private String endDate;
    private String eventDesc;
    private String eventType;
    private String prizeType;
    private String img;
    private Boolean expired;

}
