package com.spharos.pointapp.event.infrastructure;

import com.spharos.pointapp.event.domain.EventList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventListRepository extends JpaRepository<EventList, Long> {
    @Query(value = "select * from event_list e LEFT JOIN user u on e.user_id = u.id where uuid = :uuid", nativeQuery = true)
    List<EventList> findAllByUuid(@Param("uuid")String uuid);
}
