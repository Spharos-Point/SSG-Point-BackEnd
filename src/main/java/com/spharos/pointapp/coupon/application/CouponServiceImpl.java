package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.domain.*;
import com.spharos.pointapp.coupon.dto.*;
import com.spharos.pointapp.coupon.infrastructure.CouponListRepository;
import com.spharos.pointapp.coupon.infrastructure.CouponRepository;
import com.spharos.pointapp.event.domain.Event;
import com.spharos.pointapp.event.dto.EventGetDto;
import com.spharos.pointapp.event.dto.EventListGetDto;
import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static com.spharos.pointapp.coupon.domain.QCoupon.coupon;
import static com.spharos.pointapp.user.domain.QUser.user;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponListRepository couponListRepository;
    private final UserRepository userRepository;



//    쿠폰 생성
    @Override
    @Convert(converter = CouponTypeConverter.class)
    public void createCoupon(CouponCreateDto couponCreateDto) {
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(couponCreateDto.getCouponType());
        log.info("CouponType :{}", couponType);
        couponRepository.save(Coupon.builder()
                .couponName(couponCreateDto.getCouponName())
                .couponDesc(couponCreateDto.getCouponDesc())
                .regDate(couponCreateDto.getRegDate())
                .endDate(couponCreateDto.getEndDate())
                .partner(couponCreateDto.getPartner())
                .couponNum(couponCreateDto.getCouponNum())
                .couponType(couponType)
                .couponValue(couponCreateDto.getCouponValue())
                .couponImg(couponCreateDto.getCouponImg())
                .couponLogoImg(couponCreateDto.getCouponLogoImg())
                .couponValueImg(couponCreateDto.getCouponValueImg())
                .build());
    }

//    쿠폰 수정

    @Convert(converter = CouponTypeConverter.class)
    public void updateCoupon(CouponUpdateDto couponUpdateDto, Long couponId) {
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(couponUpdateDto.getCouponType());
        couponRepository.save(Coupon.builder()
                .Id(couponId)
                .couponName(couponUpdateDto.getCouponName())
                .couponDesc(couponUpdateDto.getCouponDesc())
                .regDate(couponUpdateDto.getRegDate())
                .endDate(couponUpdateDto.getEndDate())
                .partner(couponUpdateDto.getPartner())
                .couponNum(couponUpdateDto.getCouponNum())
                .couponType(couponType)
                .couponValue(couponUpdateDto.getCouponValue())
                .couponImg(couponUpdateDto.getCouponImg())
                .couponLogoImg(couponUpdateDto.getCouponLogoImg())
                .couponValueImg(couponUpdateDto.getCouponValueImg())
                .build());
    }

//    쿠폰 전체 조회

    @Override
    public List<CouponGetDto> getCoupons() {
        List<Coupon> couponList = couponRepository.findAll();
        log.info("{}", couponList);
        List<CouponGetDto> couponGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        couponList.forEach(
                coupon -> couponGetDtoList.add(
                    mapper.map(coupon, CouponGetDto.class)
                )
        );
        return couponGetDtoList;
    }

//    사용자가 보유한 쿠폰 조회
@Override
@Convert(converter = CouponTypeConverter.class)
public List<CouponGetDto> getCouponByUser(Long userId) {
    List<CouponList> couponListList = couponListRepository.findAllByUserId(userId);
    log.info("{}", couponListList);
    List<CouponGetDto> couponGetDtoList = couponListList.stream().map(item -> {
        Coupon coupon = couponRepository.findById(item.getId()).orElseThrow();
        String couponType = new CouponTypeConverter().convertToDatabaseColumn(coupon.getCouponType());
        return CouponGetDto.builder()
                .id(coupon.getId())
                .couponName(coupon.getCouponName())
                .couponDesc(coupon.getCouponDesc())
                .regDate(coupon.getRegDate())
                .endDate(coupon.getEndDate())
                .partnerId(coupon.getPartner().getId())
                .partnerName(coupon.getPartner().getPartnerName())
                .couponNum(coupon.getCouponNum())
                .couponType(couponType)
                .couponImg(coupon.getCouponImg())
                .couponLogoImg(coupon.getCouponLogoImg())
                .couponValue(coupon.getCouponValue())
                .couponValueImg(coupon.getCouponValueImg())
                .build();
    }).toList();

    log.info("Coupon List is : {}", couponGetDtoList);
    return couponGetDtoList;
}

//    쿠폰 삭제

    @Override
    public void deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
    }


//    쿠폰 다운로드
    @Override
    public void downCoupon(CouponDownDto couponDownDto, String uuid) {
        if (uuid != null && couponDownDto.getCoupon() != null) {
            // CouponList 엔티티를 생성하고 User와 Coupon을 연결한 후 저장
            CouponList couponList = CouponList.builder()
                    .user(couponDownDto.getUser())
                    .coupon(couponDownDto.getCoupon())
                    .couponStat(false)
                    .build();

            couponListRepository.save(couponList);
        }
    }

//    마감 임박순 쿠폰 조회
    @Override
    public List<CouponGetDto> getCouponByAsc() {
        List<Coupon> couponList = couponRepository.findAllByOrderByEndDateAsc();
        log.info("{}", couponList);
        List<CouponGetDto> couponGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        couponList.forEach(
                coupon -> couponGetDtoList.add(
                        mapper.map(coupon, CouponGetDto.class)
                )
        );
        log.info("{}", couponGetDtoList);
        return couponGetDtoList;
    }

    //    사용완료 또는 기간만료 쿠폰 조회
    @Override
    public List<CouponGetDto> getCouponByUserAndStat(Long userId) {
        List<CouponList> couponListList = couponListRepository.findAllByUserIdAndCouponStatFalse(userId);
        List<CouponGetDto> couponGetDtoList = couponListList.stream().map(item -> {
        Coupon coupon = couponRepository.findById(item.getId()).orElseThrow();
        String couponType = new CouponTypeConverter().convertToDatabaseColumn(coupon.getCouponType());
        return CouponGetDto.builder()
                .id(coupon.getId())
                .couponName(coupon.getCouponName())
                .couponDesc(coupon.getCouponDesc())
                .regDate(coupon.getRegDate())
                .endDate(coupon.getEndDate())
                .partnerId(coupon.getPartner().getId())
                .partnerName(coupon.getPartner().getPartnerName())
                .couponNum(coupon.getCouponNum())
                .couponType(couponType)
                .couponImg(coupon.getCouponImg())
                .couponLogoImg(coupon.getCouponLogoImg())
                .couponValue(coupon.getCouponValue())
                .couponValueImg(coupon.getCouponValueImg())
                .build();
    }).toList();
        return couponGetDtoList;
    }
}
