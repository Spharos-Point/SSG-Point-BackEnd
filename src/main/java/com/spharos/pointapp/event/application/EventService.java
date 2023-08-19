package com.spharos.pointapp.event.application;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventCreateDto;

import java.util.List;

public interface EventService {
    void createEvent(EventCreateDto eventceatedto);
    List<EventCreateDto> getEventByUser(Long userId);
    List<Event> getAllEvent();
}
