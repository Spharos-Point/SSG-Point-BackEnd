package com.spharos.pointapp.userpoint.gift.infrastructure;

import com.spharos.pointapp.userpoint.gift.domain.PointGift;
import com.spharos.pointapp.userpoint.gift.domain.PointGiftType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointGiftRepository extends JpaRepository<PointGift, Long> {
    Optional<PointGift> findTopByReceiverUuidAndPointGiftTypeOrderByIdDesc(String receiverUuid, PointGiftType pointGiftType);

    Optional<PointGift> findByPointId(Long pointId);

}
