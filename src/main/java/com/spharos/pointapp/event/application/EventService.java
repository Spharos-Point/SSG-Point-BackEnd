package com.spharos.pointapp.event.application;

import com.spharos.pointapp.event.dto.EventCreateDto;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;

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

}


