package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateGetDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AffiliateService {

    //  생성
    ResponseEntity<String> addAffiliate(AffiliateAddDto affiliateAddDto);

    //  수정
    void updateAffiliate(AffiliateUpdateDto affiliateupdateDto);

    //   유저별 조회
    List<AffiliateGetDto> getAllAffiliateCards(String uuid);

    //    삭제
    void deleteAffiliate(Long affiliateId);

}
