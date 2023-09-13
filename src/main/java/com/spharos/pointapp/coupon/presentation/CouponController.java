package com.spharos.pointapp.coupon.presentation;

import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.coupon.application.CouponService;
import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.dto.CouponDownDto;
import com.spharos.pointapp.coupon.dto.CouponGetDto;
import com.spharos.pointapp.coupon.dto.CouponUpdateDto;
import com.spharos.pointapp.coupon.infrastructure.CouponRepository;
import com.spharos.pointapp.coupon.vo.CouponCreate;
import com.spharos.pointapp.coupon.vo.CouponDown;
import com.spharos.pointapp.coupon.vo.CouponGetOut;
import com.spharos.pointapp.coupon.vo.CouponUpdate;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.event.vo.EventUpdate;
import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.infrastructure.PartnerRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CouponController {

    private final CouponService couponService;
    private final PartnerRepository partnerRepository;
    private final CouponRepository couponRepository;
    private final TokenUtils tokenUtils;
    private final UserRepository userRepository;


//    쿠폰 생성
    @PostMapping("/coupon")
    public void createCoupon(@RequestBody CouponCreate couponCreate) {
        log.info("INPUT Object Data is : {}" , couponCreate);
//        Long partnerId = partnerRepository.findByPartnerName(couponCreate.getPartnerId());

        Partner partner = partnerRepository.findById(couponCreate.getPartnerId())
                .orElseThrow(() -> new IllegalArgumentException("Partner not found with ID: " + couponCreate.getPartnerId()));
        log.info("Partner {}", partner);
        CouponCreateDto couponCreateDto = CouponCreateDto.builder()
                .couponName(couponCreate.getCouponName())
                .couponDesc(couponCreate.getCouponDesc())
                .partner(partner)
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
    public void deleteCoupon(@RequestParam(name = "couponId", defaultValue = "") Long couponId) {
        couponService.deleteCoupon(couponId);
    }

//    쿠폰 전체 조회
    @GetMapping("/couponPage")
    public List<CouponGetOut> getAllCoupons() {
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

//    사용자가 보유한 쿠폰 조회
    @Transactional(readOnly = true)
    @GetMapping("/benefits/myCoupon/{userId}")
    public List<CouponGetOut> getCouponByUser(@PathVariable("userId") Long userId) {
        log.info("{}", userId);
        ModelMapper mapper = new ModelMapper();
        List<CouponGetDto> couponGetDtoList = couponService.getCouponByUser(userId);
        List<CouponGetOut> couponGetOutList = new ArrayList<>();
        couponGetDtoList.forEach(
                couponGetDto -> couponGetOutList.add(
                        mapper.map(couponGetDto, CouponGetOut.class)
                )
        );
        return couponGetOutList;
    }

//    쿠폰 다운로드
//    @PostMapping("/couponPage")
//    public void downCoupon(@RequestHeader("Authorization") String token,
//                           @RequestBody CouponDown couponDown) {
//        String uuid = tokenUtils.extractUuidFromToken(token);
//        User user = userRepository.findByUuid(uuid).get();
//        log.info("{}", uuid);
//        Coupon coupon = couponRepository.findByCouponId(couponDown.getCouponId());
//        CouponDownDto couponDownDto = CouponDownDto.builder()
//                .user(user)
//                .coupon(coupon)
//                .build();
//        couponService.downCoupon(couponDownDto, uuid);
//    }

}
