package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateGetDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import com.spharos.pointapp.affiliatecard.infrastructure.AffiliateRepository;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AffiliateServiceImpl implements AffiliateService {

    private final AffiliateRepository affiliateRepository;
    private final ExtraRepository extraRepository;
    //    제휴 포인트카드 생성
    //    대한항공의 경우 '영문성'이라는 데이터를 추가로 받는지 어떻게 해야 하는지?
    @Override
    public ResponseEntity<String> addAffiliate(AffiliateAddDto affiliateAddDto) {
        List<AffiliateCard> affiliateCardsByUser = affiliateRepository.findAllByUuid(affiliateAddDto.getUuid());
        // 이미 같은 extraId를 가진 카드가 있는지 확인
        boolean cardExists = affiliateCardsByUser.stream().anyMatch(affiliateCard ->
                affiliateCard.getExtra().getId().equals(affiliateAddDto.getExtra().getId()));

        if (cardExists) {
            log.info("Card with the same extraId already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card with the same extraId already exists");
        }

        // 동일한 extraId를 가진 카드가 없으면 카드를 생성
        AffiliateCard newCard = AffiliateCard.builder()
                .extra(affiliateAddDto.getExtra())
                .affiliateNum(affiliateAddDto.getAffiliateNum())
                .uuid(affiliateAddDto.getUuid())
                .build();

        affiliateRepository.save(newCard);

        log.info("New card has been added");
        return ResponseEntity.status(HttpStatus.CREATED).body("New card has been added");
    }


    //    제휴포인트 카드 수정
    @Override
    public void updateAffiliate(AffiliateUpdateDto affiliateupdateDto) {
        AffiliateCard affiliateCard = affiliateRepository.findTopByUuidAndExtraId(
                affiliateupdateDto.getUuid(), affiliateupdateDto.getExtraId());
        affiliateRepository.save(
                AffiliateCard.builder()
                        .Id(affiliateCard.getId())
                        .extra(affiliateCard.getExtra())
                        .uuid(affiliateCard.getUuid())
                        .affiliateNum(affiliateupdateDto.getAffiliateNum())
                        .build()
        );
        log.info("{}", affiliateCard);

    }

    @Override
    public List<AffiliateGetDto> getAllAffiliateCards(String uuid) {
        List<AffiliateCard> affiliateCardList = affiliateRepository.findAllByUuid(uuid);
        List<AffiliateGetDto> affiliateGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        affiliateCardList.forEach(
                affiliateCard -> affiliateGetDtoList.add(
                        mapper.map(affiliateCard, AffiliateGetDto.class)
                )
        );
        return affiliateGetDtoList;
    }

    @Override
    public void deleteAffiliate(Long affiliateId) {
        affiliateRepository.deleteById(affiliateId);
    }

}


