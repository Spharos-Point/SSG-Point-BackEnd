package com.spharos.pointapp.userpoint.purchase.presentaion;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.event.vo.EventListRes;
import com.spharos.pointapp.user.dto.UserUpdateInfoDto;
import com.spharos.pointapp.userpoint.purchase.dto.*;
import com.spharos.pointapp.userpoint.purchase.application.PointPurchaseService;
import com.spharos.pointapp.userpoint.purchase.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/point/purchase")
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

    @Operation(summary = "포인트 일반 적립 조회", description = "구매한 포인트 적립 조회")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @GetMapping("/point/PurchaseUserHistory")
    public BaseResponse<List<PointPurchaseResponse>> getPointPurchase(@RequestHeader("Authorization") String token) {

        String uuid = tokenUtils.extractUuidFromToken(token);

        try {
            List<PointPurchaseResDto> pointPurchaseResDtoList = pointPurchaseService.getPointPurchaseByUuid(uuid);

            List<PointPurchaseResponse> pointPurchaseResponseList = pointPurchaseResDtoList.stream().map(
                    item -> {
                        PointPurchaseResponse pointPurchaseResponse = modelMapper.map(item, PointPurchaseResponse.class);
                        return pointPurchaseResponse;
                    }
            ).toList();

            return new BaseResponse<>(pointPurchaseResponseList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}