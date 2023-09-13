package com.spharos.pointapp.event.vo;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
public class EventCreate {
    private String eventName;
    private String regDate;
    private String endDate;
    private String eventDesc;
    private String eventType;
    private String prizeType;
    private String img;
    private Boolean expired;
}
