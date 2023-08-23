package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.dto.CouponUpdateDto;
import com.spharos.pointapp.coupon.vo.CouponCreate;

public interface CouponService {
//    쿠폰 생성
    void createCoupon(CouponCreateDto couponCreateDto);

//    쿠폰 수정
    void updateCoupon(CouponUpdateDto couponUpdateDto, Long couponId);

//    쿠폰 삭제
    void deleteCoupon(Long couponId);
}
