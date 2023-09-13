package com.spharos.pointapp.event.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventGetOut {
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
