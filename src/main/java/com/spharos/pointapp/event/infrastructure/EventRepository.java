package com.spharos.pointapp.event.infrastructure;

import com.spharos.pointapp.event.domain.Event; // "Event" 클래스 임포트
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
