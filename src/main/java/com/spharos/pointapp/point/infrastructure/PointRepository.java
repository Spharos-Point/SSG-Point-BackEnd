package com.spharos.pointapp.point.infrastructure;

import com.spharos.pointapp.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long>{
    List<Point> findByUuid(String uuid);

    @Query(value = "SELECT p.totalPoint FROM Point p WHERE p.uuid = :uuid ORDER BY p.createAt DESC LIMIT 1")
    Optional<Integer> findTotalPointByUuid(@Param("uuid")String uuid);

}
