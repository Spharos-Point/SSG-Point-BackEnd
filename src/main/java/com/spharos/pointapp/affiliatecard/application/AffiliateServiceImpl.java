package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import com.spharos.pointapp.affiliatecard.infrastructure.AffiliateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AffiliateServiceImpl implements AffiliateService {

    private final AffiliateRepository affiliateRepository;

//    제휴 포인트카드 생성
    @Override
    public void addAffiliate(AffiliateAddDto affiliateAddDto) {
        List<AffiliateCard> affiliateCardsByUser = affiliateRepository.findAllByUuid(affiliateAddDto.getUuid());
        if ( affiliateCardsByUser.stream().filter(
                affiliateCard ->  { return affiliateCard.getExtra().getId().equals(affiliateAddDto.getExtra().getId()); }).count() > 0
        ) {
            log.info("Has already been added");
            return;
        }

        affiliateRepository.save(AffiliateCard.builder()
                .extra(affiliateAddDto.getExtra())
                .affiliateNum(affiliateAddDto.getAffiliateNum())
                .uuid(affiliateAddDto.getUuid())
                .build());
    }

    //    제휴 포인트카드 수정
//    @Override
//    public void updateAffiliate(AffiliateUpdateDto affiliateUpdateDto, String uuid) {
//        affiliateRepository.save(AffiliateCard.builder()
//                .affiliateId(affiliateUpdateDto.getAffiliateId())
//                .extraId(affiliateUpdateDto.getExtraId())
//                .affiliateNum(affiliateUpdateDto.getAffiliateNum())
//                .uuid(uuid)
//                .build());
//    }
}
