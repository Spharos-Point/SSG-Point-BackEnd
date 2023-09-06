package com.spharos.pointapp.affiliatecard.application;

import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;

public interface AffiliateService {

    //  생성
    void addAffiliate(AffiliateAddDto affiliateAddDto);

    //  수정
    void updateAfiliate(AffiliateAddDto affiliateAddDto, Long id);


}
