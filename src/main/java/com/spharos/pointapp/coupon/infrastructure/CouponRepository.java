package com.spharos.pointapp.coupon.infrastructure;

import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.coupon.domain.CouponList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
//    Coupon findByCouponId(Long couponId);
}
