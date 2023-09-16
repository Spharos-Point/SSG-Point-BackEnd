package com.spharos.pointapp.userpoint.pointList.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.userpoint.pointList.application.UserPointListService;
import com.spharos.pointapp.userpoint.pointList.dto.UserPointListResDto;
import com.spharos.pointapp.userpoint.pointList.vo.UserPointResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserPointListController {

    private final UserPointListService userPointListService;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음
    @Operation(summary = "유저 포인트 전체 조회", description = "유저 포인트 전체 조회")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @GetMapping("/userPointList")
    public BaseResponse<List<UserPointResVo>> getUserPointList(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        try {
            List<UserPointListResDto> userPointList = userPointListService.getUserPointListByUuid(uuid);
            log.info("userPointList : {}", userPointList);
            List<UserPointResVo> userPointResVoList = new ArrayList<>();
            userPointList.forEach(
                    userPointListResDto -> {
                        userPointResVoList.add(
                        UserPointResVo.builder()
                                .id(userPointListResDto.getId())
                                .pointType(userPointListResDto.getPointType())
                                .pointId(userPointListResDto.getPointId())
                                .pointTypeById(userPointListResDto.getPointTypeById())
                                .createAt(userPointListResDto.getCreateAt())
                                .updateAt(userPointListResDto.getUpdateAt())
                                .build());
                    });

            return new BaseResponse<>(userPointResVoList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @Operation(summary = "유저 포인트 조회", description = "유저별 최종 포인트 합계")
    @SecurityRequirement(name = "Bearer Auth")
    @GetMapping("/userPointList/total")
    public BaseResponse<Integer> getUserPointTotal(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        try {
            Integer totalPoint = userPointListService.getTotalPointByUuid(uuid);
            return new BaseResponse<>(totalPoint);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
