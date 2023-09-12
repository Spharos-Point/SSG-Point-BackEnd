package com.spharos.pointapp.userpoint.trans.infrastructure;

import com.spharos.pointapp.userpoint.trans.domain.PointTrans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointTransRepository extends JpaRepository<PointTrans, Long> {
}

