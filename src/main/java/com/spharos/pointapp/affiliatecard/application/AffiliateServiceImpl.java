package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import com.spharos.pointapp.affiliatecard.infrastructure.AffiliateRepository;
import com.spharos.pointapp.extra.domain.Extra;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AffiliateServiceImpl implements AffiliateService {

    private final AffiliateRepository affiliateRepository;
    private final ExtraRepository extraRepository;

    //    제휴 포인트카드 생성
    @Override
    public void addAffiliate(AffiliateAddDto affiliateAddDto) {
        List<AffiliateCard> affiliateCardsByUser = affiliateRepository.findAllByUuid(affiliateAddDto.getUuid());
        if (affiliateCardsByUser.stream().filter(
                affiliateCard -> {
                    return affiliateCard.getExtra().getId().equals(affiliateAddDto.getExtra().getId());
                }).count() > 0
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
}

//    @Override
//    public void updateAffiliate(AffiliateUpdateDto affiliateupdateDto, String uuid, String affiliateCardId) {
//        log.info("{}", affiliateCardId, uuid);
//        AffiliateCard affiliateCard = affiliateRepository.findByUuidAndAffiliateId(uuid, affiliateCardId)
//        affiliateRepository.save(
//          AffiliateCard.builder()
//                  .Id(affiliateupdateDto.getId())
//                  .affiliateNum(affiliateupdateDto.getAffiliateNum())
//                  .build()
//        );
//        log.info("{}", affiliateCard);
//    }
//}

    //    제휴포인트 카드 수정
//    UUID와 extraId가 동일한 항목을 찾아서 수정
//    @Override
//    public void updateAffiliate(AffiliateUpdateDto affiliateUpdateDto, String affiliateCardId, String uuid, Long extraId) {
//        log.info("{}", affiliateCardId);
//        Optional<AffiliateCard> optionalAffiliateCard  = affiliateRepository.findByUuidAndId(affiliateCardId, uuid);
//
//        if (optionalAffiliateCard .isPresent()) {
//            AffiliateCard affiliateCard = optionalAffiliateCard.get();
//            Extra extra = extraRepository.findById(extraId).orElse(null);
//            affiliateRepository.save(AffiliateCard.builder()
//                .Id(affiliateCard.getId())
//                .uuid(affiliateCard.getUuid())
//                .extra(extra)
//                .affiliateNum(affiliateUpdateDto.getAffiliateNum())
//                .build());
//
//            log.info("포인트카드 수정 완료: {}", affiliateCard);
//        } else {
//            log.error("해당 포인트카드를 찾지 못했습니다.");
//        }

//        affiliateRepository.save(AffiliateCard.builder()
//                .Id(affiliateCard.getId())
//                .uuid(affiliateCard.getUuid())
//                .extra(affiliateUpdateDto.getExtraId())
//                .affiliateNum(affiliateUpdateDto.getAffiliateNum())
//                .build());



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
//}
