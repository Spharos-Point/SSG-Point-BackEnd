package com.spharos.pointapp.event.application;

import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;

import java.util.List;

public interface EventService {
    void createEvent(EventCreateDto eventCreateDto);
    EventGetDto getEvent(Long eventId);
    List<EventGetDto> getEvents();

}


