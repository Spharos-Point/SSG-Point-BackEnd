//package com.spharos.pointapp.pointgift.presentation;
//
//import com.spharos.pointapp.pointgift.application.PointGiftService;
//import com.spharos.pointapp.pointgift.vo.PointGiftInVo;
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
//
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
//
//
//    @PostMapping("/point/gift")
//    public void createPointGift(@RequestHeader("Authorization") String token,
//                                @RequestBody PointGiftInVo pointGiftInVo) {
//
//    }
//
//
//}
