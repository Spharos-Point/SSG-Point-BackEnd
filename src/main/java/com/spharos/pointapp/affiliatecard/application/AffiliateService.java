package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import org.springframework.http.ResponseEntity;

public interface AffiliateService {

    //  생성
    ResponseEntity<String> addAffiliate(AffiliateAddDto affiliateAddDto);

    //  수정
    void updateAffiliate(AffiliateUpdateDto affiliateupdateDto, String uuid);


}
