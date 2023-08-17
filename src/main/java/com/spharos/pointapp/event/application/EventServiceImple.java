package com.spharos.pointapp.event.application;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventCreateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImple implements EventService {
    @Override
    public void createEvent(EventCreateDto eventceatedto) {

    }

    @Override
    public List<EventCreateDto> getEventByUser(Long userId) {
        return null;
    }

    @Override
    public List<Event> getAllEvent() {
        return null;
    }
}
