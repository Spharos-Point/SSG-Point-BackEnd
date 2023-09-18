package com.spharos.pointapp.event.dto;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class EventPartiDto {

    private Event event;
    private User user;

}
