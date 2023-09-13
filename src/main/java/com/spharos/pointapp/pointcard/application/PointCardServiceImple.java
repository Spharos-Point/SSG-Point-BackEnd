package com.spharos.pointapp.pointcard.application;

import com.spharos.pointapp.pointcard.domain.PointCard;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
import com.spharos.pointapp.pointcard.infrastructure.PointCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
                .brandId(pointCardCreateDto.getBrandId())
                .branchId(pointCardCreateDto.getBranchId())
                .cvc(pointCardCreateDto.getCvc())
                .cardnumber(pointCardCreateDto.getCardNumber())
                .uuid(pointCardCreateDto.getUuid())
                        .build();
        pointCardRepository.save(pointcard);
    }

    // 2. 포인트 카드 조회
    @Override
    public List<PointCardOutDto> getPointCardByUser(String uuid) {
            List<PointCard> PointCardList = pointCardRepository.findAllByUuidOrderByCreateAtDesc(uuid);
            log.info("PointCardList {} ", PointCardList);
            List<PointCardOutDto> pointCardOutDtoList = new ArrayList<>();
            ModelMapper mapper = new ModelMapper();
            PointCardList.forEach(
                    pointCard -> pointCardOutDtoList.add(
                            mapper.map(pointCard, PointCardOutDto.class)
                    )
            );
            return pointCardOutDtoList;
    }

}