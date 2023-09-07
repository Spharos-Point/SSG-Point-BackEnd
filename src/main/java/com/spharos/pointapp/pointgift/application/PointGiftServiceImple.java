//package com.spharos.pointapp.pointgift.application;
//
//import com.spharos.pointapp.pointcard.domain.PointCard;
//import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
//import com.spharos.pointapp.pointgift.dto.GiftCreateDto;
//import com.spharos.pointapp.pointgift.infrastructure.PointGiftRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//@Slf4j
//public class PointGiftServiceImple implements PointGiftService{
//
//    private final PointGiftRepository pointGiftRepository;
//    private final ModelMapper modelMapper;
//
//
//    // 1. 선물 포인트 생성
//    @Override
//    public void createGiftPoint(GiftCreateDto giftCreateDto, String Uuid) {
//        GiftCreateDto pointCreate = GiftCreateDto.builder()
//                .point(giftCreateDto.getPoint())
//                .imageId(giftCreateDto.getImageId())
//                .type("0")
//                .giverUuid(Uuid)
//                .giftMessage(giftCreateDto.getGiftMessage())
//                .build();
//    }
//
//    // 2. 선물 포인트 보내기
//    public void giftPoint(PointCardCreateDto pointCardCreateDto) {
//
//        PointCard pointcard = PointCard.builder()
//                .partnerName(pointCardCreateDto.getPartnerName())
//                .uuid(pointCardCreateDto.getUuid())
//                .barcode(pointCardCreateDto.getBarcode())
//                .registeredStore(pointCardCreateDto.getRegisteredStore())
//                .build();
//    }
//
//
//
//}
