package com.spharos.pointapp.userpoint.pointList.infrastructure;

import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserPointListRepository extends JpaRepository<UserPointList, Long> , JpaSpecificationExecutor<UserPointList> {

    Optional<UserPointList> findTopByUuidOrderByCreateAtDesc(String uuid);
    Optional<UserPointList> findByPointId(Long pointId);
    List<UserPointList> findByUuid(String uuid);

    List<UserPointList> findByUuidAndPointTypeAndCreateAtBetween(
            String uuid,
            PointType pointType,
            LocalDateTime startDate, LocalDateTime endDate);

}
