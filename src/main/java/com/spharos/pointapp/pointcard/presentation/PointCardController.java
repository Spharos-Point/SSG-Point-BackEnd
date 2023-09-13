package com.spharos.pointapp.pointcard.presentation;

import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.partner.vo.PartnerGet;
import com.spharos.pointapp.pointcard.application.PointCardService;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
import com.spharos.pointapp.pointcard.vo.PointCardCreateInVo;
import com.spharos.pointapp.pointcard.vo.PointCardOutVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class PointCardController {

    private final PointCardService pointCardService;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음

    // 1.포인트 카드 생성
    @Operation(summary = "온라인 포인트 카드 생성", description = "유저의 온라인 카드를 생성합니다.")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PostMapping("/mypoint/regPntCard")
    public void createPointCard(@RequestHeader("Authorization") String token,
                                @RequestBody PointCardCreateInVo pointCardCreateInVo) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .branchId(pointCardCreateInVo.getBranchId())
                .cardNumber(pointCardCreateInVo.getCardNumber())
                .cvc(pointCardCreateInVo.getCvc())
                .brandId(pointCardCreateInVo.getBrandId())
                .uuid(uuid)
                .build();
        pointCardService.createPointCard(pointCardCreateDto);
    }

    // 2. 온라인 카드 전체 조회
    @Operation(summary = "온라인 포인트 카드 유저 전체 조회", description = "유저의 온라인 카드를 조회합니다.")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @GetMapping("/myinfo/cardManage")
    public BaseResponse<List<PointCardOutVo>> readCardHistory (@RequestHeader("Authorization") String token) {

        String uuid = tokenUtils.extractUuidFromToken(token);
        try {
            ModelMapper mapper = new ModelMapper();
            List<PointCardOutDto> pointcardDtoList = pointCardService.getPointCardByUser(uuid);
            log.info("{}", pointcardDtoList);

            List<PointCardOutVo> pointCardOutVo = new ArrayList<>();
            pointcardDtoList.forEach(
                    pointcardDto -> pointCardOutVo.add(
                            mapper.map(pointcardDto, PointCardOutVo.class)
                    )
            );
            return new BaseResponse<>(pointCardOutVo);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}