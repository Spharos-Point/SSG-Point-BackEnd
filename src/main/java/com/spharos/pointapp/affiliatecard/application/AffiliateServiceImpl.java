package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
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
    public void addAffiliate(AffiliateAddDto affiliateAddDto) {
        affiliateRepository.save(AffiliateCard.builder()
                .extra(affiliateAddDto.getExtra())
                .affiliateNum(affiliateAddDto.getAffiliateNum())
                .build());
    }

    @Override
    public void updateAfiliate(AffiliateAddDto affiliateAddDto, Long id) {

    }
}
