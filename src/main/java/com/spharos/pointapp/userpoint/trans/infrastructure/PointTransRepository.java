package com.spharos.pointapp.userpoint.trans.infrastructure;
import com.spharos.pointapp.userpoint.trans.domain.PointTrans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointTransRepository extends JpaRepository<PointTrans, Long> {
    Optional<PointTrans> findByPointId(Long pointId);
}