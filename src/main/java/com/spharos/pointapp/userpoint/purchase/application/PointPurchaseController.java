package com.spharos.pointapp.userpoint.purchase.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseDto;
import com.spharos.pointapp.userpoint.purchase.presentaion.PointPurchaseService;
import com.spharos.pointapp.userpoint.purchase.vo.PointPurchaseAddRequest;
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
public class PointPurchaseController {

    private final PointPurchaseService pointPurchaseService;
    private final ModelMapper modelMapper;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음

    @Operation(summary = "포인트 일반 적립", description = "영수증 등으로 구매하여 포인트 적립")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PostMapping("/point-purchase")
    public BaseResponse<String> purchasePoint(@RequestHeader("Authorization") String token,
                                              @RequestBody PointPurchaseAddRequest pointPurchaseAddRequest) {

        String uuid = tokenUtils.extractUuidFromToken(token);
        try {
            pointPurchaseService.purchasePoint(
                    modelMapper.map(pointPurchaseAddRequest, PointPurchaseDto.class), uuid);
            return new BaseResponse<>("포인트 일반 적립 성공");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());

        }

    }
}