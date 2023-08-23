package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.coupon.domain.CouponType;
import com.spharos.pointapp.coupon.domain.CouponTypeConverter;
import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.dto.CouponUpdateDto;
import com.spharos.pointapp.coupon.infrastructure.CouponRepository;
import jakarta.persistence.Convert;
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
    @Convert(converter = CouponTypeConverter.class)
    public void createCoupon(CouponCreateDto couponCreateDto) {
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(couponCreateDto.getCouponType());
        couponRepository.save(Coupon.builder()
                .couponName(couponCreateDto.getCouponName())
                .couponDesc(couponCreateDto.getCouponDesc())
                .usePlace(couponCreateDto.getUsePlace())
                .couponNum(couponCreateDto.getCouponNum())
                .couponType(couponType)
                .couponValue(couponCreateDto.getCouponValue())
                .build());
    }

    @Convert(converter = CouponTypeConverter.class)
    public void updateCoupon(CouponUpdateDto couponUpdateDto, Long couponId) {
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(couponUpdateDto.getCouponType());
        couponRepository.save(Coupon.builder()
                .Id(couponId)
                .couponName(couponUpdateDto.getCouponName())
                .couponDesc(couponUpdateDto.getCouponDesc())
                .usePlace(couponUpdateDto.getUsePlace())
                .couponNum(couponUpdateDto.getCouponNum())
                .couponType(couponType)
                .couponValue(couponUpdateDto.getCouponValue())
                .build());
    }

    @Override
    public void deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
    }

}
