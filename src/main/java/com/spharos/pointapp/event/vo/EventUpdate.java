package com.spharos.pointapp.event.vo;

import lombok.*;

@Getter
@ToString
public class EventUpdate {
    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;
}
