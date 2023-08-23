package com.spharos.pointapp.event.vo;

import lombok.*;

@Getter
@ToString
public class EventUpdate {
    private Long eventId;
    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;
}
