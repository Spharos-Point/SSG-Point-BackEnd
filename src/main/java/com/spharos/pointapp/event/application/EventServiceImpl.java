package com.spharos.pointapp.event.application;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.infrastructure.EventListRepository;
import com.spharos.pointapp.event.infrastructure.EventRepository;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventListRepository eventListRepository;

//    이벤트 생성
    @Override
    public void createEvent(EventCreateDto eventCreateDto) {
        eventRepository.save(
                Event.builder()
                        .eventDesc(eventCreateDto.getEventDesc())
                        .eventName(eventCreateDto.getEventName())
                        .eventType(eventCreateDto.getEventType())
                        .prizeType(eventCreateDto.getPrizeType())
                        .build()
        );
    }

    //    이벤트 수정
    public void updateEvent(EventUpdateDto eventUpdateDto, Long eventId) {
        log.info("{}", eventId);

        Event event = eventRepository.findById(eventId).get();
        eventRepository.save(
          Event.builder()
                  .Id(eventId)
                  .eventName(eventUpdateDto.getEventName())
                  .eventDesc(eventUpdateDto.getEventDesc())
                  .eventType(eventUpdateDto.getEventType())
                  .prizeType(eventUpdateDto.getPrizeType())
                  .build()
        );
        log.info("{}", event);
    }


//    이벤트 삭제
//    @Override
//    public EventCreateDto deleteEvent(Long eventId) {
//        return null;
//}


    //    이벤트 개별 조회
    @Override
    public EventGetDto getEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        log.info("{}", event);
        ModelMapper mapper = new ModelMapper();
        EventGetDto eventGetDto = mapper.map(event, EventGetDto.class);
        log.info("{}", eventGetDto);
        return eventGetDto;
    }

//    이벤트 전체 조회
    @Override
    public List<EventGetDto> getEvents() {
        List<Event> eventList = eventRepository.findAll();
        log.info("{}", eventList);
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        eventList.forEach(
                event -> eventGetDtoList.add(
                        mapper.map(event, EventGetDto.class)
                )
        );
        log.info("{}", eventGetDtoList);
        return eventGetDtoList;
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }


}
