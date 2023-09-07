package com.spharos.pointapp.pointcard.infrastructure;

import com.spharos.pointapp.pointcard.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointCardRepository extends JpaRepository<PointCard, Long> {

//    1. 바코드 유효성 검사
    Optional<PointCard> findByBarcode(String barcode);

//    2. 포인트 카드 조회
    List<PointCard> findByUuid(String uuid);

//    void deleteById(String uuid);
}
