package com.spharos.pointapp.event.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventGetDto {

    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;

}
