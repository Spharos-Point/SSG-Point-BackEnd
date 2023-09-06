package com.spharos.pointapp.pointgift.infrastructure;

import com.spharos.pointapp.pointgift.domain.GiftPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointGiftRepository extends JpaRepository<GiftPoint, Long> {

}
