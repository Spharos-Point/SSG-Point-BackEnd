package com.spharos.pointapp.event.presentation;

import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.coupon.dto.CouponDownDto;
import com.spharos.pointapp.coupon.vo.CouponDown;
import com.spharos.pointapp.event.application.EventService;
import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.*;
import com.spharos.pointapp.event.infrastructure.EventRepository;
import com.spharos.pointapp.event.vo.*;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final UserRepository userRepository;
    private final TokenUtils tokenUtils;
    private final EventRepository eventRepository;
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
    @GetMapping("/benefits/myEvent")
    public List<EventListRes> getEventByUser(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        User user = userRepository.findByUuid(uuid).get();
        log.info("{}", user);
        List<EventListGetDto> eventLiatGetDtoList = eventService.getEventByUser(user.getId());
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
//        List<EventListRes> eventListResList = eventLiatGetDtoList.stream().map(
//                item -> {
//                    EventListRes eventListRes = mapper.map(item, EventListRes.class);
//                    return eventListRes;
//                }
//        ).toList();

        List<EventListRes> eventListResList = new ArrayList<>();
        eventLiatGetDtoList.forEach(
                EventListGetDto -> eventListResList.add(
                        mapper.map(EventListGetDto, EventListRes.class)
                )
        );
        return eventListResList;
    }

//    종료 이벤트 조회
    @Operation(summary = "종료된 이벤트 조회", description = "종료된 이벤트를 조회합니다.", tags = { "Event Controller" })
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
    @Operation(summary = "마감 임박순 조회", description = "이벤트를 마감 임박순으로 조회합니다.", tags = { "Event Controller" })
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
    @Operation(summary = "당첨 발표", description = "종료된 참여형 이벤트를 조회합니다.", tags = { "Event Controller" })
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
    @Operation(summary = "진행중 이벤트 조회", description = "종료되지 않은 진행중 이벤트를 조회합니다.", tags = { "Event Controller" })
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

//    사용자가 당첨된 이벤트 조회
    @Operation(summary = "사용자 당첨 이벤트 조회", description = "사용자가 당첨된 이벤트를 조회합니다.", tags = { "Event Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @GetMapping("/benefits/winEvent")
    public List<EventListRes> getEventByPrize(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        User user = userRepository.findByUuid(uuid).get();
        log.info("{}", user);
        List<EventListGetDto> eventGetDtoList = eventService.getEventByPrize(user.getId());
        ModelMapper mapper = new ModelMapper();
        log.info("{}", eventGetDtoList);
        List<EventListRes> eventListResList = new ArrayList<>();
        eventGetDtoList.forEach(
                EventListGetDto -> eventListResList.add(
                        mapper.map(EventListGetDto, EventListRes.class)
                )
        );
        return eventListResList;
    }

//    이벤트 참여
    @Operation(summary = "이벤트 참여", description = "이벤트에 참여합니다.", tags = { "Event Controller" })
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/ingevents")
    public void partiEvent(@RequestHeader("Authorization") String token,
                           @RequestBody EventParti eventParti) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        User user = userRepository.findByUuid(uuid).get();
        log.info("{}", uuid);
        Event event = eventRepository.findById(eventParti.getEventId()).get();
        EventPartiDto eventPartiDto = EventPartiDto.builder()
                .user(user)
                .event(event)
                .build();
        eventService.partiEvent(eventPartiDto, uuid);
    }

}
