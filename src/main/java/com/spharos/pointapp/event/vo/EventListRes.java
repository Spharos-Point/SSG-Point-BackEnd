package com.spharos.pointapp.event.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventListRes {
    private Long id;
    private Long eventId;
    private Long userId;
    private Boolean prize;
}
