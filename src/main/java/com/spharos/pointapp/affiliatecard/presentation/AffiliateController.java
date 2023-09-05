package com.spharos.pointapp.affiliatecard.presentation;

import com.spharos.pointapp.affiliatecard.application.AffiliateService;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.vo.AffiliateAdd;
import com.spharos.pointapp.partner.dto.PartnerCreateDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AffiliateController {

    public final AffiliateService affiliateService;

    @PostMapping("/mypoint/regAffiliatePntCard")
    public void addAffilicate(@RequestBody AffiliateAdd affiliateAdd) {
        log.info("{}", affiliateAdd);
        ModelMapper mapper = new ModelMapper();
        AffiliateAddDto affiliateAddDto = mapper.map(affiliateAdd, AffiliateAddDto.class);
        affiliateService.addAffilicate(affiliateAddDto);
    }

}
