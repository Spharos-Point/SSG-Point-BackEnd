package com.spharos.pointapp.event.vo;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
public class EventCreate {
    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;
}
