package com.spharos.pointapp.userpoint.gift.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.userpoint.gift.application.PointGiftService;
import com.spharos.pointapp.userpoint.gift.dto.*;
import com.spharos.pointapp.userpoint.gift.vo.*;
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
public class PointGiftController {

    private final PointGiftService pointGiftService;
    private final ModelMapper modelMapper;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음


    /**
     * 1. 포인트 선물 생성
     * 2. 포인트 선물 유저 확인
     * 3. 포인트 대기 조회
     * 4. 포인트 선물 수락
     * 5. 포인트 선물 거절
     * 6. 포인트 선물 조회
     */

    @Operation(summary = "포인트 선물 생성", description = "대기 상태로 포인트를 생성합니다.")
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/gift/createPoint")
    public BaseResponse<?> purchasePoint(@RequestHeader("Authorization") String token,
                                         @RequestBody PointGiftInVo PointGiftInVo) {

        String uuid = tokenUtils.extractUuidFromToken(token);
        log.info("PointGiftInVo : {}", PointGiftInVo.getReceiverLoginId());
        try {
            pointGiftService.giftPoint(
                    modelMapper.map(PointGiftInVo, PointGiftCreateDto.class), uuid);
            return new BaseResponse<>("포인트 선물 보내기 성공");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @Operation(summary = "포인트 선물 유저 확인(유저 이름, 유저 휴대폰 번호 조회)", description = "선물 대상자 확인하기")
    @SecurityRequirement(name = "Bearer Auth")
    @GetMapping("/gift/searchSenderUser")
    public BaseResponse<String> searchSenderUser(@RequestHeader("Authorization") String token,
                                                 @RequestParam("userName") String senderUserName,
                                                 @RequestParam("phoneNumber") String senderPhoneNumber) {
        log.info("searchSenderUser : {} {}", senderUserName, senderPhoneNumber);

        String uuid = tokenUtils.extractUuidFromToken(token);
        try {
            String loginId = pointGiftService.getreceiverUser(senderUserName, senderPhoneNumber, uuid);
            return new BaseResponse<>(loginId);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @Operation(summary = "포인트 선물 대기 조회", description = "가장 최근 포인트 선물 기록 조회")
    @SecurityRequirement(name = "Bearer Auth")
    @GetMapping("/gift/waiting")
    public BaseResponse<PointGiftLastOutVo> searchPendingGift(@RequestHeader("Authorization") String token) {
        String receiverUuid = tokenUtils.extractUuidFromToken(token);
        log.info("receiverUuid : {}", receiverUuid);
        try {
            PointGiftLastDto pointGiftLastDto = pointGiftService.getLastGift(receiverUuid);
            PointGiftLastOutVo pointGiftLastOutVo = modelMapper.map(pointGiftLastDto, PointGiftLastOutVo.class);
            log.info("pointGiftLastOutVo : {}", pointGiftLastOutVo);

            return new BaseResponse<>(pointGiftLastOutVo);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
    @Operation(summary = "포인트 선물 수락", description = "선물 상태 변경 W -> A")
    @SecurityRequirement(name = "Bearer Auth")
    @PutMapping("/gift/accept")
    public BaseResponse<String> giftAccept(@RequestHeader("Authorization") String token) {
        String receiverUuid = tokenUtils.extractUuidFromToken(token);

        try {
            pointGiftService.updateSuccessGiftPoint(receiverUuid);
            return new BaseResponse<>("포인트 수락 성공");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @Operation(summary = "포인트 선물 거절", description = "선물 상태 변경 W -> R")
    @SecurityRequirement(name = "Bearer Auth")
    @PutMapping("/gift/refuse")
    public BaseResponse<String> giftRefuse(@RequestHeader("Authorization") String token) {
        String receiverUuid = tokenUtils.extractUuidFromToken(token);
        try {
            pointGiftService.updateCancelGiftPoint(receiverUuid);
            return new BaseResponse<>("포인트 거절 성공");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

//    @Operation(summary = "포인트 선물 조회", description = "포인트 선물 조회")
//    @SecurityRequirement(name = "Bearer Auth")
//    @GetMapping("/gift/search")
//    public BaseResponse<String> giftSearch(@RequestHeader("Authorization") String token) {
//        String receiverUuid = tokenUtils.extractUuidFromToken(token);
//        try {
//            pointGiftService.updateCancelGiftPoint(receiverUuid);
//            return new BaseResponse<>();
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }

}

