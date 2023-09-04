package com.spharos.pointapp.PointPlus.infrastructure;

import com.spharos.pointapp.PointPlus.domain.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long> {

}
