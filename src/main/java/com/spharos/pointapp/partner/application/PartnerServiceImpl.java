package com.spharos.pointapp.partner.application;

import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.dto.PartnerCreateDto;
import com.spharos.pointapp.partner.dto.PartnerGetDto;
import com.spharos.pointapp.partner.infrastructure.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final BranchRepository branchRepository;

//    파트너 생성
    @Override
    public void createPartner(PartnerCreateDto partnerCreateDto) {
        partnerRepository.save(
                Partner.builder()
                        .partnerName(partnerCreateDto.getPartnerName())
                        .build());
    }

//    파트너 조회

    @Override
    public List<PartnerGetDto> getPartners() {
        List<Partner> partnerList = partnerRepository.findAll();
        log.info("{}", partnerList);
        List<PartnerGetDto> partnerGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        partnerList.forEach(
                partner -> partnerGetDtoList.add(
                        mapper.map(partner, PartnerGetDto.class)
                )
        );
        log.info("{}", partnerGetDtoList);
        return partnerGetDtoList;
    }


}
