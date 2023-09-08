package com.spharos.pointapp.pointcard.application;

import com.spharos.pointapp.pointcard.domain.PointCard;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
import com.spharos.pointapp.pointcard.infrastructure.PointCardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class PointCardServiceImple implements PointCardService {

    private final PointCardRepository pointCardRepository;
    private final ModelMapper modelMapper;


    /**
     *
     * 1. 포인트 카드 생성
     * 2. 포인트 카드 조회
     */

//    1. 포인트 카드 생성
    @Override
//    @Transactional(readOnly = false)
    public void createPointCard(PointCardCreateDto pointCardCreateDto) {

        PointCard pointcard = PointCard.builder()
                .partnerName(pointCardCreateDto.getPartnerName())
                .uuid(pointCardCreateDto.getUuid())
                .barcode(pointCardCreateDto.getBarcode())
                .registeredStore(pointCardCreateDto.getRegisteredStore())
                .build();
        pointCardRepository.save(pointcard);
    }

//    2. 포인트 카드 조회
    @Override
    public List<PointCardOutDto> getPointCardByUser(String uuid) {

        List<PointCard> pointCards = pointCardRepository.findByUuid(uuid);

        List<PointCardOutDto> pointCardOutDto = pointCards.stream().map(
                pointCard -> modelMapper.map(pointCard, PointCardOutDto.class)
        ).toList();
        return pointCardOutDto;


    }


}