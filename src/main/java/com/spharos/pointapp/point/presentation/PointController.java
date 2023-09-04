package com.spharos.pointapp.point.presentation;

import com.spharos.pointapp.point.application.PointService;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.dto.PointGetDto;
import com.spharos.pointapp.point.vo.PointInVo;
import com.spharos.pointapp.point.vo.PointOutVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
    private final ModelMapper modelMapper;

    /**
     * 1. 포인트 생성
     * 2. 포인트 전체 조회
     * 3. 토탈 포인트 조회
     * 4. 포인트 적립/사용 조회
     * 5. 포인트 선물 조회
     * 6. 포인트 전환 조회
     * 7. 포인트 선물 하기
     * 8. 포인트 선물 받기
     * 9. 사용 여부
     */

//    * 1. 포인트 생성
//     * 2. 포인트 전체 조회
//     * 3. 토탈 포인트 조회
//     * 4. 포인트 선물
//     * 5. 포인트 선물 적립
//     * 6. 포인트 선물 대기 리스트 15일 뒤 취소 됨
//     * 7. 포인트 전환
//     * 8. 영수중 포인트 적립
//     *

    //  1. 포인트 생성
    @PostMapping("/point")
    void addPoint(@RequestBody PointInVo pointInVo,
                  @RequestHeader("uuid") String uuid ) {
        log.info("INPUT Object Data is : {}" , pointInVo);
        PointAddDto pointAddDto = PointAddDto.builder()
                .pointType(pointInVo.getPointType())
                .point(pointInVo.getPoint())
                .used(pointInVo.getUsed())
                .uuid(uuid)
                .build();
        pointService.createPoint(pointAddDto, uuid);
    }

    @GetMapping("/pointRead")
    public List<PointOutVo> getPointByUser(@RequestHeader("Authorization") String token) {

        List<PointGetDto> pointListByUser = pointService.getPointByUser(token);
        List<PointOutVo> pointOutList = pointListByUser.stream().map(pointGetDto -> PointOutVo.builder()
                .pointType(pointGetDto.getPointType())
                .point(pointGetDto.getPoint())
                .used(pointGetDto.getUsed())
                .build()).toList();
        log.info("OUTPUT pointOutList is : {}" , pointOutList);
        return pointOutList;
    }

    @GetMapping("/point/all")
    public List<Point> getAllPoint() {
        return pointService.getAllPoint();
    }
}
