package com.spharos.pointapp.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateDto {

    private String eventName;
    private String eventDesc;
    private LocalDateTime eventRegDate;
    private LocalDateTime eventEndDate;
    private Integer eventType;
    private String prizeType;

}
