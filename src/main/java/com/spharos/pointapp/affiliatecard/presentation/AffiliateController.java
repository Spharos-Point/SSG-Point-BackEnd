package com.spharos.pointapp.affiliatecard.presentation;

import com.spharos.pointapp.affiliatecard.application.AffiliateService;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import com.spharos.pointapp.affiliatecard.vo.AffiliateAdd;
import com.spharos.pointapp.affiliatecard.vo.AffiliateUpdate;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.extra.domain.Extra;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AffiliateController {

    private final JwtTokenProvider jwtTokenProvider;
    public final AffiliateService affiliateService;
    public final ExtraRepository extraRepository;

    // 레포지토리에서 원하는 값을 가져와서 프론트에서 가져온 값을 비교

//    제휴포인트 카드 생성
    @Operation(summary = "제휴포인트 카드 등록", description = "제휴포인트 카드를 등록합니다.", tags = { "Affiliate Controller" })
    @PostMapping("/mypoint/regAffiliatePntCard")
    public void addAffiliate(@RequestHeader("Authorization") String token,
                             @RequestBody AffiliateAdd affiliateAdd) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        log.info("UUID {}", uuid);
        Extra extra = extraRepository.findById(affiliateAdd.getExtraId()).get();
//                .orElseThrow(() -> new IllegalArgumentException("Extra not found with ID: " + affiliateAdd.getExtraId()));
        log.info("Extra {}", extra);
        if (extra != null) {
            AffiliateAddDto affiliateAddDto = AffiliateAddDto.builder()
                    .affiliateNum(affiliateAdd.getAffiliateNum())
                    .extra(extra)
                    .uuid(uuid)
                    .build();
            affiliateService.addAffiliate(affiliateAddDto);
        } else {
            throw new IllegalArgumentException("이미 존재하는 제휴 카드가 있습니다.");
        }
    }


//    제휴포인트 카드 수정
//    @PutMapping("/mypoint/regAffiliatePntCard")
//    public void updateAffiliate(@RequestHeader("Authorization") String token,
//                                @RequestBody AffiliateUpdate affiliateUpdate) {
//        String uuid = jwtTokenProvider.getUuid(token.substring(7));
//        log.info("UUID {}", uuid);
//        ModelMapper mapper = new ModelMapper();
//        AffiliateUpdateDto affiliateUpdateDto = mapper.map(affiliateUpdate, AffiliateUpdateDto.class);
//        affiliateService.updateAffiliate(affiliateUpdateDto, affiliateUpdateDto.getAffiliateNum()); //
//    }

}

//    제휴포인트 카드 수정
//    @PutMapping("/mypoint/regAffiliatePntCard")
//    public void updateAffiliate(@RequestBody AffiliateUpdate affiliateUpdate) {
//        log.info("{}", affiliateUpdate);
//        Long extraId = extraRepository.findByExtraId(affiliateUpdate.getExtraName());
//        AffiliateUpdateDto affiliateUpdateDto = AffiliateUpdateDto.builder()
//                .affiliateNum(affiliateUpdate.getAffiliateNum())
//                .extraId(extraId)
//                .build();
//
//    }