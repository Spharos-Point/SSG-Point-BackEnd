package com.spharos.pointapp.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class EventCreateDto {

    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;

}
