package com.spharos.pointapp.partner.application;

import com.spharos.pointapp.partner.dto.PartnerCreateDto;
import com.spharos.pointapp.partner.dto.PartnerGetDto;

import java.util.List;

public interface PartnerService {

//    파트너 생성
    void createPartner(PartnerCreateDto partnerCreateDto);

//    파트너 조회
    List<PartnerGetDto> getPartners();
}
