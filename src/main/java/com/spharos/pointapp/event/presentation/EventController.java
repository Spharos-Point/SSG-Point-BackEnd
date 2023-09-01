package com.spharos.pointapp.event.presentation;

import com.spharos.pointapp.event.application.EventService;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventListGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.vo.EventCreate;
import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.event.vo.EventListRes;
import com.spharos.pointapp.event.vo.EventUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

//    이벤트 생성
    @PostMapping("/event")
    public void createEvent(@RequestBody EventCreate eventCreate) {
        log.info("{}", eventCreate);
        ModelMapper mapper = new ModelMapper();
        EventCreateDto eventCreateDto = mapper.map(eventCreate, EventCreateDto.class);
        eventService.createEvent(eventCreateDto);

    }

//    이벤트 수정 API 정의서에 event?eventid=1의 형식으로 사용
    @PutMapping("/event")
    public void updateEvent(@RequestBody EventUpdate eventUpdate) {
        log.info("{}", eventUpdate);
        ModelMapper mapper = new ModelMapper();
        EventUpdateDto eventUpdateDto = mapper.map(eventUpdate, EventUpdateDto.class);
        eventService.updateEvent(eventUpdateDto, eventUpdate.getEventId());
    }

//     이벤트 개별 조회
    @GetMapping("/event/{eventId}")
    public EventGetOut getEventByEventId(@PathVariable("eventId")  Long eventId) {
        log.info("{}", eventId);
        ModelMapper mapper = new ModelMapper();
        EventGetDto eventGetDto = eventService.getEvent(eventId);
        return mapper.map(eventGetDto, EventGetOut.class);
    }

//    이벤트 전체 조회
    @GetMapping("/event/all")
    private List<EventGetOut> getAllEvents() {
        ModelMapper mapper = new ModelMapper();
        List<EventGetDto> eventGetDtoList = eventService.getEvents();
        log.info("{}", eventGetDtoList);
        List<EventGetOut> eventGetOutList = new ArrayList<>();
        eventGetDtoList.forEach(
                eventGetDto -> eventGetOutList.add(
                    mapper.map(eventGetDto, EventGetOut.class)
                )
        );
        return eventGetOutList;
    }

//    이벤트 삭제
    @DeleteMapping("/event")
    private void deleteEvent(@RequestParam(name = "eventId", defaultValue = "")  Long eventId) {
        eventService.deleteEvent(eventId);
    }

//    사용자가 참여한 이벤트 조회
    @Transactional(readOnly = true)
    @GetMapping("/benefits/myEvent/{userId}")
    public List<EventListRes> getEventByUser(@PathVariable("userId") Long userId) {
        log.info("{}", userId);
        List<EventListGetDto> eventLiatGetDtoList = eventService.getEventByUser(userId);
        ModelMapper mapper = new ModelMapper();
        log.info("{}", eventLiatGetDtoList);
//        List<EventListRes> eventListResList = new ArrayList<>();
//            eventLiatGetDtoList.forEach(
//                eventListGetDtoItem ->
//                     eventListResList.add(
//                             EventListRes.builder()
//                            .id(eventListGetDtoItem.getId())
//                            .userId(eventListGetDtoItem.getUserId())
//                            .eventId(eventListGetDtoItem.getEventId())
//                            .prize(eventListGetDtoItem.getPrize())
//                            .build())
//            );
        List<EventListRes> eventListResList = eventLiatGetDtoList.stream().map(
                item -> {
                    EventListRes eventListRes = mapper.map(item, EventListRes.class);
                    return eventListRes;
                }
        ).toList();



        return eventListResList;
    }


}
