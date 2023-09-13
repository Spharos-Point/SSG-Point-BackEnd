package com.spharos.pointapp.event.infrastructure;

import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventGetDto;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

//    @Query("SELECT e FROM Event e WHERE e.endDate >= CURRENT_DATE() ORDER BY e.endDate ASC")
    List<Event> findAllByOrderByEndDateAsc();

    @Query(value = "SELECT * FROM event where event.expired = 1 order by end_date", nativeQuery = true)
    List<Event> findAllByExpired();

    @Query("SELECT e FROM Event e WHERE e.eventType = '참여'")
    List<Event> findByEventType();


}
