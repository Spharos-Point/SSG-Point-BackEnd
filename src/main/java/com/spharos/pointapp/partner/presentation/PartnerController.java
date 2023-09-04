package com.spharos.pointapp.partner.presentation;


import com.spharos.pointapp.event.vo.EventGetOut;
import com.spharos.pointapp.partner.application.PartnerService;
import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.dto.PartnerCreateDto;
import com.spharos.pointapp.partner.dto.PartnerGetDto;
import com.spharos.pointapp.partner.vo.PartnerCreate;
import com.spharos.pointapp.partner.vo.PartnerGet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PartnerController {

    private final PartnerService partnerService;

//    파트너 생성
    @PostMapping("/partner")
    public void createPartner(@RequestBody PartnerCreate partnerCreate) {
        log.info("{}", partnerCreate);
        ModelMapper mapper = new ModelMapper();
        PartnerCreateDto partnerCreateDto = mapper.map(partnerCreate, PartnerCreateDto.class);
        partnerService.createPartner(partnerCreateDto);
    }

//    파트너 조회
    @GetMapping("/partner/all")
    public List<PartnerGet> getAllPartners() {
        ModelMapper mapper = new ModelMapper();
        List<PartnerGetDto> partnerGetDtoList = partnerService.getPartners();
        log.info("{}", partnerGetDtoList);
        List<PartnerGet> partnerGetList = new ArrayList<>();
        partnerGetDtoList.forEach(
                partnerGetDto -> partnerGetList.add(
                        mapper.map(partnerGetDto, PartnerGet.class)
                )
        );
        return partnerGetList;
    }

}
