package com.spharos.pointapp.event.application;

import com.spharos.pointapp.coupon.dto.CouponGetDto;
import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventListGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    //    이벤트 생성
    void createEvent(EventCreateDto eventCreateDto);

    //    이벤트 수정
    void updateEvent(EventUpdateDto eventUpdateDto, Long eventId);

    //    이벤트 개별 조회
    EventGetDto getEvent(Long eventId);

    //    이벤트 전체 조회
    List<EventGetDto> getEvents();

    //    이벤트 삭제
    void deleteEvent(Long eventId);

//    사용자가 참여한 이벤트
    List<EventListGetDto> getEventByUser(Long userId);

//    종료 이벤트 조회
    List<EventGetDto> getEventByExpired();

//    역순 정렬 조회
    List<EventGetDto> getEventByDesc();

//    진행 중 이벤트 조회
    List<EventGetDto> getEventByWin();

//    종료된 이벤트를 제외한 이벤트 조회
    List<EventGetDto> getEventByNotExpired();



}


