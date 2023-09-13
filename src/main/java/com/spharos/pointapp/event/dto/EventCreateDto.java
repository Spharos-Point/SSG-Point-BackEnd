package com.spharos.pointapp.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class EventCreateDto {

    private String eventName;
    private String regDate;
    private String endDate;
    private String eventDesc;
    private String eventType;
    private String prizeType;
    private String img;
    private Boolean expired;

}
