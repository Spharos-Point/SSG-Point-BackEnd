package com.spharos.pointapp.pointplus.infrastructure;

import com.spharos.pointapp.pointplus.domain.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long> {

}
