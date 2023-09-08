package com.spharos.pointapp.userpoint.gift.infrastructure;

import com.spharos.pointapp.userpoint.gift.domain.PointGift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointGiftRepository extends JpaRepository<PointGift, Long> {
}
