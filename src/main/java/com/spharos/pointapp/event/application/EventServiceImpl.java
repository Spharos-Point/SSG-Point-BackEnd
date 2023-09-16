package com.spharos.pointapp.event.application;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.domain.EventList;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventListGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.infrastructure.EventListRepository;
import com.spharos.pointapp.event.infrastructure.EventRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                        .regDate(eventCreateDto.getRegDate())
                        .endDate(eventCreateDto.getEndDate())
                        .img(eventCreateDto.getImg())
                        .eventName(eventCreateDto.getEventName())
                        .eventType(eventCreateDto.getEventType())
                        .prizeType(eventCreateDto.getPrizeType())
                        .expired(eventCreateDto.getExpired())
                        .build()
        );
    }

    //    이벤트 수정
    public void updateEvent(EventUpdateDto eventUpdateDto, Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        eventRepository.save(
          Event.builder()
                  .Id(eventId)
                  .eventName(eventUpdateDto.getEventName())
                  .regDate(eventUpdateDto.getRegDate())
                  .endDate(eventUpdateDto.getEndDate())
                  .img(eventUpdateDto.getImg())
                  .eventDesc(eventUpdateDto.getEventDesc())
                  .eventType(eventUpdateDto.getEventType())
                  .prizeType(eventUpdateDto.getPrizeType())
                  .expired(eventUpdateDto.getExpired())
                  .build()
        );
    }

    //    이벤트 개별 조회
    @Override
    public EventGetDto getEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        ModelMapper mapper = new ModelMapper();
        EventGetDto eventGetDto = mapper.map(event, EventGetDto.class);
        return eventGetDto;
    }

//    이벤트 전체 조회
    @Override
    public List<EventGetDto> getEvents() {
        List<Event> eventList = eventRepository.findAll();
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        eventList.forEach(
                event -> eventGetDtoList.add(
                        mapper.map(event, EventGetDto.class)
                )
        );
        return eventGetDtoList;
    }

//    이벤트 삭제
    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

//    사용자가 참여한 이벤트 조회(당첨X)
    @Override
    public List<EventListGetDto> getEventByUser(Long userId) {
        List<EventList> eventListList = eventListRepository.findAllByUserIdAndPrizeFalse(userId);
        List<EventListGetDto> eventListGetDtoList = eventListList.stream().map(item -> {
            Event event = eventRepository.findById(item.getEvent().getId()).orElseThrow();

            return EventListGetDto.builder()
                    .id(item.getId())
                    .eventName(event.getEventName())
                    .img(event.getImg())
                    .endDate(event.getEndDate())
                    .eventType(event.getEventType())
                    .eventDesc(event.getEventDesc())
                    .expired(event.getExpired())
                    .regDate(event.getRegDate())
                    .prizeType(event.getPrizeType())
                    .build();
        }).toList();
        return eventListGetDtoList;
    }

    //    종료 이벤트 조회
    @Override
    public List<EventGetDto> getEventByExpired() {
        List<Event> eventList = eventRepository.findAllByExpiredAndEventType();
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        eventList.forEach(
                event -> eventGetDtoList.add(
                        mapper.map(event, EventGetDto.class)
                )
        );
        return eventGetDtoList;
    }

//    마감 임박순 조회
    @Override
    public List<EventGetDto> getEventByDesc() {
        List<Event> eventList = eventRepository.findAllByOrderByEndDateAsc();
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        eventList.forEach(
                event -> eventGetDtoList.add(
                        mapper.map(event, EventGetDto.class)
                )
        );
        return eventGetDtoList;
    }

    //    진행중 이벤트 조회
    @Override
    public List<EventGetDto> getEventByWin() {
        List<Event> eventList = eventRepository.findByEventTypeAndExpired();
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        eventList.forEach(
                event -> eventGetDtoList.add(
                        mapper.map(event, EventGetDto.class)
                )
        );
        return eventGetDtoList;
    }

//    종료된 이벤트를 제외한 이벤트 조회
    @Override
    public List<EventGetDto> getEventByNotExpired() {
        List<Event> eventList = eventRepository.findAllByNotExpired();
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        eventList.forEach(
                event -> eventGetDtoList.add(
                        mapper.map(event, EventGetDto.class)
                )
        );
        return eventGetDtoList;
    }

//    사용자가 당첨된 이벤트 조회
    @Override
    public List<EventListGetDto> getEventByPrize(Long userId) {
        List<EventList> eventListList = eventListRepository.findAllByUserIdAndPrizeTrue(userId);
        List<EventListGetDto> eventListGetDtoList = eventListList.stream().map(item -> {
            Event event = eventRepository.findById(item.getEvent().getId()).orElseThrow();

            return EventListGetDto.builder()
                    .id(item.getId())
                    .eventName(event.getEventName())
                    .img(event.getImg())
                    .endDate(event.getEndDate())
                    .eventType(event.getEventType())
                    .eventDesc(event.getEventDesc())
                    .expired(event.getExpired())
                    .regDate(event.getRegDate())
                    .prizeType(event.getPrizeType())
                    .build();
        }).toList();
        return eventListGetDtoList;

    }
}
