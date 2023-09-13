package com.spharos.pointapp.userpoint.trans.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.userpoint.trans.dto.PointTransDto;
import com.spharos.pointapp.userpoint.trans.dto.PointTransResDto;
import com.spharos.pointapp.userpoint.trans.presentation.PointTransService;
import com.spharos.pointapp.userpoint.trans.vo.PointTransAddRequest;
import com.spharos.pointapp.userpoint.trans.vo.PointTransResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PointTransController {

    private final PointTransService pointTransService;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenUtils tokenUtils;

    @Operation(summary = "포인트 전환", description = "포인트를 전환합니다")
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/point-trans")
    public BaseResponse<?> transPoint(@RequestHeader("Authorization") String token,
            @RequestBody PointTransAddRequest pointTransAddRequest) {
        log.info("transPoint");
        String uuid = tokenUtils.extractUuidFromToken(token);

        try {
            PointTransDto pointTransDto = PointTransDto.builder()
                    .transPoint(pointTransAddRequest.getTransPoint())
                    .extraId(pointTransAddRequest.getExtraId())
                    .uuid(uuid)
                    .build();
            pointTransService.transPoint(pointTransDto, uuid);
            return new BaseResponse<>("Success");
        } catch ( BaseException exception ) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @Operation(summary = "포인트전환 POINTTRANSID로 데이터 가져오기", description = "포인트전환 POINTTRANSID로 데이터를 가져옵니다.")
    @SecurityRequirement(name = "Bearer Auth")
    @GetMapping("/point-trans")
    public BaseResponse<PointTransResponse> getPointTrans(@RequestParam(name = "pointTransId") Long pointTransId
    ) {
        try {
            PointTransResDto pointTransResDto = pointTransService.getPointTransByPointTransId(pointTransId);
            PointTransResponse pointTransResponse = PointTransResponse.builder()
                    .id(pointTransResDto.getId())
                    .pointId(pointTransResDto.getPoint().getId())
                    .extraId(pointTransResDto.getExtra().getId())
                    .transPoint(pointTransResDto.getTransPoint())
                    .build();
            return new BaseResponse<>(pointTransResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}