package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.dto.CouponDownDto;
import com.spharos.pointapp.coupon.dto.CouponGetDto;
import com.spharos.pointapp.coupon.dto.CouponUpdateDto;
import com.spharos.pointapp.coupon.vo.CouponCreate;

import java.util.List;

public interface CouponService {
//    쿠폰 생성
    void createCoupon(CouponCreateDto couponCreateDto);

//    쿠폰 수정
    void updateCoupon(CouponUpdateDto couponUpdateDto, Long couponId);

//    쿠폰 삭제
    void deleteCoupon(Long couponId);

//    쿠폰 전체 조회
    List<CouponGetDto> getCoupons();

//    사용자가 보유한 쿠폰 조회
    List<CouponGetDto> getCouponByUser(Long userId);

    //    사용자 쿠폰 다운로드
    void downCoupon(CouponDownDto couponDownDto);

}
