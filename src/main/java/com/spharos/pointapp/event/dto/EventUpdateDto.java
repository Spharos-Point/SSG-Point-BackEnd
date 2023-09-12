package com.spharos.pointapp.event.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventUpdateDto {

    private String eventName;
    private String eventDesc;
    private String eventType;
    private String prizeType;
}
