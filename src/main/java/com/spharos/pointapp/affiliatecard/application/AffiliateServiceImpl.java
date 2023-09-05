package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.affiliatecard.domain.AffiliateType;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.infrastructure.AffiliateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AffiliateServiceImpl implements AffiliateService {

    private final AffiliateRepository affiliateRepository;

//    제휴 포인트카드 생성
    @Override
    public void addAffilicate(AffiliateAddDto affilicateAddDto) {
//        affiliateRepository.save(AffiliateCard.builder()
//                .affiliateType(AffiliateType)
//                .affilicateNum(affilicateAddDto.getAffiliateNum())
//                .build());
    }
}
