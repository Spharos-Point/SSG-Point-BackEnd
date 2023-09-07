//package com.spharos.pointapp.pointgift.presentation;
//
//import com.spharos.pointapp.config.security.JwtTokenProvider;
//import com.spharos.pointapp.pointgift.application.PointGiftService;
//import com.spharos.pointapp.pointgift.dto.*;
//import com.spharos.pointapp.pointgift.vo.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping("/api/v1")
//public class PointGiftController {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final PointGiftService pointGiftService;
//    private final ModelMapper modelMapper;
//
//    /**
//     *
//     * 1. 포인트 선물 생성
//     * 2. 포인트 선물 수락
//     * 3. 포인트 선물 거절
//     * 4. 포인트 선물 대기 15일 뒤 취소 됨
//     * 5. 포인트 전체 선물 내역 조회
//     * 6. 포인트 적립 내역 조회
//     * 7. 포인트 사용 내역 조회
//     *
//     */
//    @Operation(summary = "포인트 선물 생성 및 보내기", description = "포인트 선물 생성 확인", tags = { "PointGift Controller" })
//    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
//    @PostMapping("/point/gift")
//    public void PointGift(@RequestHeader("Authorization") String token,
//                                @RequestBody GiftInVo giftInVo) {
//        String uuid = jwtTokenProvider.getUuid(token.substring(7));
//        GiftCreateDto giftCreateDto = modelMapper.map(giftInVo, GiftCreateDto.class);
//        pointGiftService.createPointCard(giftCreateDto, uuid);
//    }
//
//    @Operation(summary = "포인트 선물 수락", description = "포인트 선물 수락 확인", tags = { "PointGift Controller" })
//    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
//    @PostMapping("/point/receive")
//    public void PointGift(@RequestHeader("Authorization") String token) {
//
//    }
//}
