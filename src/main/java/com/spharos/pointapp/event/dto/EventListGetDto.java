package com.spharos.pointapp.event.dto;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.user.domain.User;
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
