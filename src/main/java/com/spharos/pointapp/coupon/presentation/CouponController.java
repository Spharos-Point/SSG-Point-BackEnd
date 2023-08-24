package com.spharos.pointapp.coupon.presentation;

import com.spharos.pointapp.coupon.application.CouponService;
import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.dto.CouponGetDto;
import com.spharos.pointapp.coupon.dto.CouponUpdateDto;
import com.spharos.pointapp.coupon.vo.CouponCreate;
import com.spharos.pointapp.coupon.vo.CouponGetOut;
import com.spharos.pointapp.coupon.vo.CouponUpdate;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.event.vo.EventUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CouponController {

    private final CouponService couponService;

//    쿠폰 생성
    @PostMapping("/coupon")
    public void createCoupon(@RequestBody CouponCreate couponCreate) {
        log.info("INPUT Object Data is : {}" , couponCreate);
        CouponCreateDto couponCreateDto = CouponCreateDto.builder()
                .couponName(couponCreate.getCouponName())
                .couponDesc(couponCreate.getCouponDesc())
                .usePlace(couponCreate.getUsePlace())
                .couponNum(couponCreate.getCouponNum())
                .couponType(couponCreate.getCouponType())
                .couponValue(couponCreate.getCouponValue())
                .build();
        couponService.createCoupon(couponCreateDto);
    }

//    쿠폰 수정
    @PutMapping("/coupon")
    public void updateCoupon(@RequestBody CouponUpdate couponUpdate) {
        log.info("{}", couponUpdate);
        ModelMapper mapper = new ModelMapper();
        CouponUpdateDto couponUpdateDto = mapper.map(couponUpdate, CouponUpdateDto.class);
        couponService.updateCoupon(couponUpdateDto, couponUpdate.getCouponId());
    }

//    쿠폰 삭제
    @DeleteMapping("/coupon")
    private void deleteCoupon(@RequestParam(name = "couponId", defaultValue = "") Long couponId) {
        couponService.deleteCoupon(couponId);
    }

//    쿠폰 조회
    @GetMapping("/couponPage")
    private List<CouponGetOut> getAllCoupons() {
        ModelMapper mapper = new ModelMapper();
        List<CouponGetDto> couponGetDtoList = couponService.getCoupons();
        log.info("{}", couponGetDtoList);
        List<CouponGetOut> couponGetOutList = new ArrayList<>();
        couponGetDtoList.forEach(
                couponGetDto -> couponGetOutList.add(
                        mapper.map(couponGetDto, CouponGetOut.class)
                )
        );
        return couponGetOutList;
    }

//    @GetMapping("/benefits/myCoupon")
//    private List<couponGetDtoList>


}
