package com.spharos.pointapp.pointgift.application;

import com.spharos.pointapp.pointgift.domain.GiftPoint;
import com.spharos.pointapp.pointgift.domain.GiftType;
import com.spharos.pointapp.pointgift.dto.GiftInDto;
import com.spharos.pointapp.pointgift.infrastructure.PointGiftRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PointGiftServiceImple implements PointGiftService{

    private final PointGiftRepository pointGiftRepository;
    private final ModelMapper modelMapper;


    // 1. 선물 포인트 생성
    @Override
    public GiftPoint createGiftPoint(GiftInDto giftInDto) {
        GiftPoint giftPoint = GiftPoint.builder()
                .giftType(GiftType.WAIT)
                .build();
        modelMapper.map(giftInDto, giftPoint);
        return giftPoint;
    }

    // 2. 포인트 선물하기
    @Override
    public void giveGiftPoint(GiftInDto giftInDto, String giverUuid) {
        giftInDto = GiftInDto.builder()  // 'builder()' 메서드로 빌더 인스턴스 생성
                .giverUuid(giverUuid)
                .build();
        pointGiftRepository.save(createGiftPoint(giftInDto));
    }


}
