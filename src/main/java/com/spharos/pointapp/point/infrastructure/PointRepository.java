package com.spharos.pointapp.point.infrastructure;

import com.spharos.pointapp.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long>{
}
