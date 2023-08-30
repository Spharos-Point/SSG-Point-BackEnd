package com.spharos.pointapp.partner.presentation;


import com.spharos.pointapp.partner.application.PartnerService;
import com.spharos.pointapp.partner.dto.PartnerCreateDto;
import com.spharos.pointapp.partner.vo.PartnerCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PartnerController {

    private final PartnerService storeService;

//    가맹점 생성
    @PostMapping("/store")
    public void createPartner(@RequestBody PartnerCreate partnerCreate) {
        log.info("{}", partnerCreate);
        ModelMapper mapper = new ModelMapper();
        PartnerCreateDto partnerCreateDto = mapper.map(partnerCreate, PartnerCreateDto.class);
        storeService.createPartner(partnerCreateDto);
    }
}
