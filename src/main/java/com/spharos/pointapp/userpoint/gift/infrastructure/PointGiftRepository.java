package com.spharos.pointapp.userpoint.gift.infrastructure;

import com.spharos.pointapp.userpoint.gift.domain.PointGift;
import com.spharos.pointapp.userpoint.gift.domain.PointGiftType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointGiftRepository extends JpaRepository<PointGift, Long> {
    Optional<PointGift> findTopBySenderUuidAndPointGiftTypeOrderByIdDesc(String uuid, PointGiftType pointGiftType);

}
