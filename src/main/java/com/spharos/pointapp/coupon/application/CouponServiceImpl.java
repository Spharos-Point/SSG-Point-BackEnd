package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.coupon.dto.CouponCreateDto.CouponTypeEnum;
import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

//    쿠폰 생성
    @Override
    public void createCoupon(CouponCreateDto couponCreateDto) {
        couponRepository.save(
                Coupon.builder()
                        .couponName(couponCreateDto.getCouponName())
                        .couponDesc(couponCreateDto.getCouponDesc())
                        .usePlace(couponCreateDto.getUsePlace())
                        .couponNum(couponCreateDto.getCouponNum())
                        .couponType(couponCreateDto.getCouponType())
                        .couponValue(couponCreateDto.getCouponValue())
                        .build()
        );
    }

}
