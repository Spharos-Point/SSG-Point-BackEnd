package com.spharos.pointapp.point.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.TokenUtils;
import com.spharos.pointapp.point.application.PointService;
import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.vo.PointInVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
    private final ModelMapper modelMapper;
    private final TokenUtils tokenUtils; // TokenUtils를 주입받음

    /**
     *
     * 1. 토탈 포인트 조회
     * 2. 포인트 타입별 적립/사용
     * 3. 포인트 적립/사용 조회 (전환, 선물 제외)
     * 4. 포인트 전체 조회
     *
     */

    // 1. 토탈 포인트 조회
    @Operation(summary = "유저 토탈 포인트 조회", description = "Integer로 토탈 포인트를 반환합니다.", tags = { "Point Controller" })
    @GetMapping("/pointRead/total")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    public BaseResponse<Integer> getTotalPointByUser(@RequestHeader("Authorization") String token) {
        String uuid = tokenUtils.extractUuidFromToken(token);

        try{
            Integer PointTotal = pointService.getPointTotalByUser(uuid);
            return new BaseResponse<>(PointTotal);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //  2. 포인트 적립/사용 (이벤트, 선물, 쿠폰, 출석, 룰렛, 전환, 제휴사, 영수증, 바코드, 소멸)
    @PostMapping("/point/general")
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    public BaseResponse<String> addPoint(@RequestHeader("Authorization") String token,
                  @RequestBody PointInVo pointInVo) {
        String uuid = tokenUtils.extractUuidFromToken(token);
        try {
            PointAddDto pointAddDto = modelMapper.map(pointInVo, PointAddDto.class);
            pointService.createPoint(pointAddDto, uuid);
            return new BaseResponse<>("포인트 적립 성공");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

//    //  3. 포인트 적립/사용 조회 (전환, 선물 제외)
//    @GetMapping("/point/earnAndUseSearch")
//    public ResponseEntity<?> earnAndUseSearch(@RequestHeader("Authorization") String token) {
//        String uuid = jwtTokenProvider.getUuid(token.substring(7));
//
//    }

}

//    //  3. 포인트 전체 조회
//    @GetMapping("/pointRead")
//    public ResponseEntity<List<PointOutVo>> getPointByUser(@RequestHeader("Authorization") String token) {
//        String uuid = jwtTokenProvider.getUuid(token.substring(7));
//        List<PointGetDto> pointListByUser = pointService.getPointByUser(uuid);
//        log.info("INPUT Object Data is : {} " , pointListByUser);
//
//        List<PointOutVo> pointOutList = pointListByUser.stream()
//                .map(pointGetDto -> modelMapper.map(pointGetDto, PointOutVo.class))
//                .toList();
//        log.info("INPUT Object Data is : {} " , pointOutList);
//        return new ResponseEntity<>(pointOutList, HttpStatus.OK);
//        }
//    }

//    public List<PointOutVo> getPointByUser(@RequestHeader("Authorization") String token) {
//        String uuid = jwtTokenProvider.getUuid(token.substring(7));
//        List<PointGetDto> pointListByUser = pointService.getPointByUser(uuid);
//
//        // ModelMapper를 사용하여 PointGetDto를 PointOutVo로 변환
//        List<PointOutVo> pointOutList = pointListByUser.stream()
//                .map(pointGetDto -> modelMapper.map(pointGetDto, PointOutVo.class))
//                .collect(Collectors.toList());
//
//        return pointOutList;
//    }

//    @GetMapping("/point/all")
//    public List<Point> getAllPoint() {
//        return pointService.getAllPoint();
//    }

