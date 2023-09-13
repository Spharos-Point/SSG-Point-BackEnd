package com.spharos.pointapp.affiliatecard.presentation;

import com.spharos.pointapp.affiliatecard.application.AffiliateService;
import com.spharos.pointapp.affiliatecard.dto.AffiliateAddDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateGetDto;
import com.spharos.pointapp.affiliatecard.dto.AffiliateUpdateDto;
import com.spharos.pointapp.affiliatecard.vo.AffiliateAdd;
import com.spharos.pointapp.affiliatecard.vo.AffiliateGet;
import com.spharos.pointapp.affiliatecard.vo.AffiliateUpdate;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.extra.domain.Extra;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AffiliateController {

    private final JwtTokenProvider jwtTokenProvider;
    public final AffiliateService affiliateService;
    public final ExtraRepository extraRepository;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음

    // 레포지토리에서 원하는 값을 가져와서 프론트에서 가져온 값을 비교

//    제휴포인트 카드 생성
    @Operation(summary = "제휴포인트 카드 등록", description = "제휴포인트 카드를 등록합니다.", tags = { "Affiliate Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PostMapping("/mypoint/regAffiliatePntCard")
    public void addAffiliate(@RequestHeader("Authorization") String token,
                             @RequestBody AffiliateAdd affiliateAdd) {
        String uuid = tokenUtils.extractUuidFromToken(token);
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
//    카드 번호를 uuid로 인식하고 service에 넘겨줘서 null값이 들어옴
    @Operation(summary = "제휴포인트 카드 수정", description = "제휴포인트 카드를 수정합니다.", tags = { "Affiliate Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PutMapping("/mypoint/regAffiliatePntCard")
    public void updateAffiliate(@RequestHeader("Authorization") String token,
                                @RequestBody AffiliateUpdate affiliateUpdate) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        log.info("UUID {}", uuid);
        affiliateService.updateAffiliate(
                AffiliateUpdateDto.builder()
                        .affiliateNum(affiliateUpdate.getAffiliateNum())
                        .extraId(affiliateUpdate.getExtraId())
                        .uuid(uuid)
                        .build()
        );

    }

    //    제휴포인트 카드 조회 추후 url은 수정할 수도 있음
    @Operation(summary = "등록한 제휴포인트 카드 조회", description = "uuid를 이용하여 제휴포인트 카드를 조회합니다.", tags = { "Affiliate Controller" })
    @GetMapping("/mypoint/regAffiliatePntCard")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    private List<AffiliateGet> getAllAffiliateCard(@RequestHeader("Authorization") String token) {
        // 토큰에서 UUID 추출
        String uuid = jwtTokenProvider.getUuid(token.substring(7));

        // ModelMapper를 사용하여 DTO 리스트
        List<AffiliateGetDto> affiliateGetDtoList = affiliateService.getAllAffiliateCards(uuid);

        // DTO 리스트를 Entity 리스트로 변환
        List<AffiliateGet> affiliateGetList = affiliateGetDtoList.stream()
                .map(affiliateGetDto -> new ModelMapper().map(affiliateGetDto, AffiliateGet.class))
                .collect(Collectors.toList());

        return affiliateGetList;
    }

    //    제휴포인트 카드 삭제
    @Operation(summary = "등록한 제휴포인트 카드 삭제", description = "affiliate Id를 이용하여 제휴포인트 카드를 삭제합니다.", tags = { "Affiliate Controller" })
    @DeleteMapping("/mypoint/regAffiliatePntCard")
    public void deleteAffiliate(@RequestParam(name = "affiliateId", defaultValue = "") Long affiliateId) {
        affiliateService.deleteAffiliate(affiliateId);
    }

}
