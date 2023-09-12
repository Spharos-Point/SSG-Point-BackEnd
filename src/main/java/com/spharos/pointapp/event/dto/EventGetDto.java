package com.spharos.pointapp.event.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventGetDto {

    private String eventName;
    private String eventDesc;
    private Integer eventType;
    private String prizeType;

}
