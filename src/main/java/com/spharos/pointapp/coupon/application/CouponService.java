package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.vo.CouponCreate;

public interface CouponService {
//    쿠폰 생성
    void createCoupon(CouponCreateDto couponCreateDto);
}
