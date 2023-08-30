package com.spharos.pointapp.partner.application;

import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.domain.PartnerName;
import com.spharos.pointapp.partner.domain.PartnerNameConverter;
import com.spharos.pointapp.partner.dto.PartnerCreateDto;
import com.spharos.pointapp.partner.infrastructure.PartnerRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    @Convert(converter = PartnerNameConverter.class)
    public void createPartner(PartnerCreateDto partnerCreateDto) {
        PartnerName partnerName = new PartnerNameConverter().convertToEntityAttribute(partnerCreateDto.getPartnerName());
        partnerRepository.save(Partner.builder()
                .partnerName(partnerName)
                .build());
    }
}
