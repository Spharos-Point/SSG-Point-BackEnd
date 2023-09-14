package com.spharos.pointapp.point.infrastructure;

import com.spharos.pointapp.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long>{

    Optional<Point> findById(Long Id);
}
