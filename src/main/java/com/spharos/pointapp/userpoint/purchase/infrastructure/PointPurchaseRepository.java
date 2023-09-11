package com.spharos.pointapp.userpoint.purchase.infrastructure;

import com.spharos.pointapp.userpoint.purchase.domain.PointPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointPurchaseRepository extends JpaRepository<PointPurchase, Long> {
}
