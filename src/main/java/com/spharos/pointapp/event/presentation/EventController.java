package com.spharos.pointapp.event.presentation;

import com.spharos.pointapp.event.application.EventService;
import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventListGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.vo.EventCreate;
import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.event.vo.EventListRes;
import com.spharos.pointapp.event.vo.EventUpdate;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

//    이벤트 생성
    @Operation(summary = "이벤트 생성", description = "새로운 이벤트를 등록합니다.", tags = { "Event Controller" })
    @PostMapping("/event")
    public void createEvent(@RequestBody EventCreate eventCreate) {
        log.info("{}", eventCreate);
        ModelMapper mapper = new ModelMapper();
        EventCreateDto eventCreateDto = mapper.map(eventCreate, EventCreateDto.class);
        eventService.createEvent(eventCreateDto);

    }

//    이벤트 수정 API 정의서에 event?eventid=1의 형식으로 사용
    @Operation(summary = "이벤트 수정", description = "이벤트를 수정합니다.", tags = { "Event Controller" })
    @PutMapping("/event")
    public void updateEvent(@RequestBody EventUpdate eventUpdate) {
        log.info("{}", eventUpdate);
        ModelMapper mapper = new ModelMapper();
        EventUpdateDto eventUpdateDto = mapper.map(eventUpdate, EventUpdateDto.class);
        eventService.updateEvent(eventUpdateDto, eventUpdate.getEventId());
    }

//     이벤트 개별 조회
    @Operation(summary = "이벤트 개별 조회", description = "event id를 이용하여 이벤트를 개별 조회합니다.", tags = { "Event Controller" })
    @GetMapping("/event/{eventId}")
    public EventGetOut getEventByEventId(@PathVariable("eventId")  Long eventId) {
        log.info("{}", eventId);
        ModelMapper mapper = new ModelMapper();
        EventGetDto eventGetDto = eventService.getEvent(eventId);
        return mapper.map(eventGetDto, EventGetOut.class);
    }

//    이벤트 전체 조회
    @Operation(summary = "이벤트 조회", description = "이벤트를 전체 조회합니다.", tags = { "Event Controller" })
    @GetMapping("/event/all")
    public List<EventGetOut> getAllEvents() {
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
    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다.", tags = { "Event Controller" })
    @DeleteMapping("/event")
    public void deleteEvent(@RequestParam(name = "eventId", defaultValue = "")  Long eventId) {
        eventService.deleteEvent(eventId);
    }

//    사용자가 참여한 이벤트 조회
    @Operation(summary = "사용자 이벤트 조회", description = "사용자가 참여한 이벤트를 조회합니다.", tags = { "Event Controller" })
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

//    종료 이벤트 조회
    @GetMapping("/endevents")
    public List<EventGetOut> getEventByExpired() {
        ModelMapper mapper = new ModelMapper();
        List<EventGetDto> eventGetDtoList = eventService.getEventByExpired();
        log.info("{}", eventGetDtoList);
        List<EventGetOut> eventGetOutList = new ArrayList<>();
        eventGetDtoList.forEach(
                eventGetDto -> eventGetOutList.add(
                        mapper.map(eventGetDto, EventGetOut.class)
                ));
        return eventGetOutList;
    }

//    마감 임박순 조회
    @GetMapping("/inevents/expired")
    public List<EventGetOut> getEventByDesc() {
        ModelMapper mapper = new ModelMapper();
        List<EventGetDto> eventGetDtoList = eventService.getEventByDesc();
        log.info("{}", eventGetDtoList);
        List<EventGetOut> eventGetOutList = new ArrayList<>();
        eventGetDtoList.forEach(
                eventGetDto -> eventGetOutList.add(
                        mapper.map(eventGetDto, EventGetOut.class)
                ));
        return eventGetOutList;
}

//    종료된 참여형 이벤트 조회
    @GetMapping("/winevents")
    public List<EventGetOut> getEventByWin() {
        ModelMapper mapper = new ModelMapper();
        List<EventGetDto> eventGetDtoList = eventService.getEventByWin();
        log.info("{}", eventGetDtoList);
        List<EventGetOut> eventGetOutList = new ArrayList<>();
        eventGetDtoList.forEach(
                eventGetDto -> eventGetOutList.add(
                        mapper.map(eventGetDto, EventGetOut.class)
                ));
        return eventGetOutList;
    }

//    종료된 이벤트를 제외한 이벤트 조회
    @GetMapping("/inevents")
    public List<EventGetOut> getEventByNotExpired() {
        ModelMapper mapper = new ModelMapper();
        List<EventGetDto> eventGetDtoList = eventService.getEventByNotExpired();
        log.info("{}", eventGetDtoList);
        List<EventGetOut> eventGetOutList = new ArrayList<>();
        eventGetDtoList.forEach(
                eventGetDto -> eventGetOutList.add(
                        mapper.map(eventGetDto, EventGetOut.class)
                ));
        return eventGetOutList;
    }

}
