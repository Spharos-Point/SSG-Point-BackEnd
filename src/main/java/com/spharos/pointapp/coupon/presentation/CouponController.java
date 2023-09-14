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
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.event.vo.EventUpdate;
import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.infrastructure.PartnerRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "쿠폰 생성", description = "쿠폰을 생성합니다.", tags = { "Coupon Controller" })
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
                .regDate(couponCreate.getRegDate())
                .endDate(couponCreate.getEndDate())
                .couponNum(couponCreate.getCouponNum())
                .couponType(couponCreate.getCouponType())
                .couponValue(couponCreate.getCouponValue())
                .couponImg(couponCreate.getCouponImg())
                .couponLogoImg(couponCreate.getCouponLogoImg())
                .couponValueImg(couponCreate.getCouponValueImg())
                .build();
        couponService.createCoupon(couponCreateDto);
    }

//    쿠폰 수정
    @Operation(summary = "쿠폰 수정", description = "쿠폰을 수정합니다.", tags = { "Coupon Controller" })
    @PutMapping("/coupon")
    public void updateCoupon(@RequestBody CouponUpdate couponUpdate) {
        log.info("{}", couponUpdate);
        ModelMapper mapper = new ModelMapper();
        CouponUpdateDto couponUpdateDto = mapper.map(couponUpdate, CouponUpdateDto.class);
        couponService.updateCoupon(couponUpdateDto, couponUpdate.getCouponId());
    }

//    쿠폰 삭제
    @Operation(summary = "쿠폰 삭제", description = "쿠폰을 삭제합니다.", tags = { "Coupon Controller" })
    @DeleteMapping("/coupon")
    public void deleteCoupon(@RequestParam(name = "couponId", defaultValue = "") Long couponId) {
        couponService.deleteCoupon(couponId);
    }

//    쿠폰 전체 조회
    @Operation(summary = "쿠폰 전체 조회", description = "생성된 모든 쿠폰을 조회합니다.", tags = { "Coupon Controller" })
    @GetMapping("/couponPage/all")
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
    @Operation(summary = "사용자가 보유한 쿠폰 조회", description = "기간에 상관없이 사용자가 보유한 모든 쿠폰을 조회합니다.", tags = { "Coupon Controller" })
    @Transactional(readOnly = true)
    @GetMapping("/benefits/myCoupon")
    public List<CouponGetOut> getCouponByUser(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        log.info("{}", uuid);
        User user = userRepository.findByUuid(uuid).get();
        ModelMapper mapper = new ModelMapper();
        List<CouponGetDto> couponGetDtoList = couponService.getCouponByUser(user.getId());
        List<CouponGetOut> couponGetOutList = new ArrayList<>();
        couponGetDtoList.forEach(
                couponGetDto -> couponGetOutList.add(
                        mapper.map(couponGetDto, CouponGetOut.class)
                )
        );
        return couponGetOutList;
    }

//    쿠폰 다운로드
    @Operation(summary = "쿠폰 다운로드", description = "쿠폰을 다운로드합니다.", tags = { "Coupon Controller" })
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/couponPage")
    public void downCoupon(@RequestHeader("Authorization") String token,
                           @RequestBody CouponDown couponDown) {
            String uuid = tokenUtils.extractUuidFromToken(token);
        User user = userRepository.findByUuid(uuid).get();
        log.info("{}", uuid);
        Coupon coupon = couponRepository.findById(couponDown.getCouponId()).get();
        CouponDownDto couponDownDto = CouponDownDto.builder()
                .user(user)
                .coupon(coupon)
                .build();
        couponService.downCoupon(couponDownDto, uuid);
    }

//    마감 임박순 쿠폰 조회
    @Operation(summary = "마감 임박순 쿠폰 조회", description = "마감이 임박한 순서대로 쿠폰을 조회합니다.", tags = { "Coupon Controller" })
    @GetMapping("/couponPage")
    public List<CouponGetOut> getCouponByAsc() {
        ModelMapper mapper = new ModelMapper();
        List<CouponGetDto> couponGetDtoList = couponService.getCouponByAsc();
        log.info("{}", couponGetDtoList);
        List<CouponGetOut> couponGetOutList = new ArrayList<>();
        couponGetDtoList.forEach(
                couponGetDto -> couponGetOutList.add(
                        mapper.map(couponGetDto, CouponGetOut.class)
                ));
        return couponGetOutList;
    }

}
