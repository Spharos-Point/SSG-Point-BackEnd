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
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public void updateAffiliate(AffiliateUpdateDto affiliateupdateDto, String uuid) {
        AffiliateCard affiliateCard = affiliateRepository.findTopByUuidAndExtraId(uuid, affiliateupdateDto.getExtraId());
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
