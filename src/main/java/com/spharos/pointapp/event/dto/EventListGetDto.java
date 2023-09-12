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
    private Long eventId;
    private Long userId;
    private Boolean prize;

}
