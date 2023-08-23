package com.spharos.pointapp.event.infrastructure;

import com.spharos.pointapp.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
