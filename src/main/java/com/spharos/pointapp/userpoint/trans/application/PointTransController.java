package com.spharos.pointapp.userpoint.trans.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.userpoint.trans.dto.PointTransDto;
import com.spharos.pointapp.userpoint.trans.presentation.PointTransService;
import com.spharos.pointapp.userpoint.trans.vo.PointTransAddRequest;
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
            PointTransDto pointTransDto = modelMapper.map(pointTransAddRequest, PointTransDto.class);
            pointTransService.transPoint(pointTransDto, uuid);
            return new BaseResponse<>("전환에 실패하였습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}