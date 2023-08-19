package com.spharos.pointapp.event.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class EventCreate {
    private String eventName;
    private String eventDesc;
    private LocalDateTime eventRegDate;
    private LocalDateTime eventEndDate;
    private Integer eventType;
    private String prizeType;
}
