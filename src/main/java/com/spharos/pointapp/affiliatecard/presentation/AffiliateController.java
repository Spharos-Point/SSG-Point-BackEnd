package com.spharos.pointapp.affiliatecard.presentation;

import com.spharos.pointapp.affiliatecard.application.AffiliateService;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.vo.AffiliateAdd;
import com.spharos.pointapp.extra.domain.Extra;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AffiliateController {

    public final AffiliateService affiliateService;
    public final ExtraRepository extraRepository;

    @PostMapping("/mypoint/regAffiliatePntCard")
    public void addAffiliate(@RequestBody AffiliateAdd affiliateAdd) {
        Extra extra = extraRepository.findById(affiliateAdd.getExtraId())
                .orElseThrow(() -> new IllegalArgumentException("Extra not found with ID: " + affiliateAdd.getExtraId()));
        log.info("Extra {}", extra);
        AffiliateAddDto affiliateAddDto = AffiliateAddDto.builder()
                .affiliateNum(affiliateAdd.getAffiliateNum())
                .extra(extra)
                .build();
        affiliateService.addAffiliate(affiliateAddDto);
    }

}
