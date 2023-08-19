package com.spharos.pointapp.event.presentation;

import com.spharos.pointapp.event.application.EventService;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.vo.EventCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    @PostMapping("event")
    public void createEvent(EventCreate eventCreate) {

        EventCreateDto eventCreateDto = EventCreateDto.builder()
                .prizeType(eventCreate.getPrizeType())
                .eventDesc(eventCreate.getEventDesc())
                .eventEndDate(eventCreate.getEventEndDate())
                .eventRegDate(eventCreate.getEventRegDate())
                .eventName(eventCreate.getEventName())
                .eventType(eventCreate.getEventType())
                .build();

        eventService.createEvent(eventCreateDto);

    }
}
