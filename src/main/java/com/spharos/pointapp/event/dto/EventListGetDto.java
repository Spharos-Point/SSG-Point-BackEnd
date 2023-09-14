package com.spharos.pointapp.event.dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventListGetDto {

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
