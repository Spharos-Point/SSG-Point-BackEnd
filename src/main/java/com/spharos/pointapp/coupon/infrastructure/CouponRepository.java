package com.spharos.pointapp.coupon.infrastructure;

import com.spharos.pointapp.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findById(Long Id);

//    마감 임박순 쿠폰 조회
    List<Coupon> findAllByOrderByEndDateAsc();

}
