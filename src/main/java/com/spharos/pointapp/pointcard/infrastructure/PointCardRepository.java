package com.spharos.pointapp.pointcard.infrastructure;

import com.spharos.pointapp.pointcard.domain.PointCard;
import com.spharos.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointCardRepository extends JpaRepository<PointCard, Long> {

    Optional<PointCard> findByBarcode(String barcode);
    Optional<User> findByUuid(String uuid);

}
