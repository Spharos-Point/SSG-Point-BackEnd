package com.spharos.pointapp.event.infrastructure;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventGetDto;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

//    @Query("SELECT e FROM Event e WHERE e.endDate >= CURRENT_DATE() ORDER BY e.endDate ASC")
    List<Event> findAllByOrderByEndDateAsc();

//    종료된 이벤트
    @Query(value = "SELECT e FROM Event e where e.expired = true AND e.eventType != '참여'")
    List<Event> findAllByExpiredAndEventType();

//    종료된 이벤트를 제외한 이벤트 조회
    @Query(value = "SELECT e FROM Event e where e.expired != true")
    List<Event> findAllByNotExpired();

//    종료된 참여 이벤트
    @Query(value = "SELECT e FROM Event e where e.eventType = '참여' AND e.expired = true")
    List<Event> findByEventTypeAndExpired();

    Optional<Event> findById(Long Id);

}
