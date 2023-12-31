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

    //  쿠폰 다운로드
    void downCoupon(CouponDownDto couponDownDto, String uuid);

//    마감 임박순 쿠폰 조회
    List<CouponGetDto> getCouponByAsc();

//    사용완료 또는 기간만료 쿠폰 조회
//    uuid로 유저를 찾아서 사용여부가 true인 것을 조회
    List<CouponGetDto> getCouponByUserAndStat(Long userId);

}
