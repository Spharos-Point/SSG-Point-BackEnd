package com.spharos.pointapp.event.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventGetOut {
    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;
}
