package com.spharos.pointapp.point.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.point.application.PointService;
import com.spharos.pointapp.point.dto.PointGetDto;
import com.spharos.pointapp.point.vo.PointOutVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음

    /**
     * 1. 토탈 포인트 조회
     * 2. 전체 포인트 내역 조회
     */

    // 1. 토탈 포인트 조회
    @Operation(summary = "유저 토탈 포인트 조회", description = "Integer로 토탈 포인트를 반환합니다.", tags = {"Point Controller"})
    @GetMapping("/pointRead/total")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    public BaseResponse<Integer> getTotalPointByUser(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);

        try {
            Integer PointTotal = pointService.getPointTotalByUser(uuid);
            return new BaseResponse<>(PointTotal);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @Operation(summary = "포인트 내역 조회", description = "포인트 내역을 반환합니다.", tags = {"Point Controller"})
    @GetMapping("/pointRead/{pointId}")
    public BaseResponse<PointOutVo> getPointListByUser(
            @PathVariable("pointId") Long pointId
    ) {
        try {
            PointGetDto pointGetDto = pointService.getPointByPointId(pointId);
            PointOutVo pointOutVo = PointOutVo.builder()
                    .point(pointGetDto.getPoint())
                    .totalPoint(pointGetDto.getTotalPoint())
                    .pointType(pointGetDto.getPointType())
                    .used(pointGetDto.getUsed())
                    .createdAt(pointGetDto.getCreatedAt())
                    .updatedAt(pointGetDto.getUpdatedAt())
                    .build();
            return new BaseResponse<>(pointOutVo);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}

