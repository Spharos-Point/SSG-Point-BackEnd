package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;

public interface AffiliateService {

    //  생성
    void addAffiliate(AffiliateAddDto affiliateAddDto);

    //  수정
//    void updateAffiliate(AffiliateUpdateDto affiliateupdateDto, String uuid, String affiliateNum);


}
