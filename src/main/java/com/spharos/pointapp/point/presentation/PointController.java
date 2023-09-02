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
     * 포인트 생성
     * 포인트 전체 조회
     * 포인트 적립/사용 조회
     * 포인트 선물 조회
     * 포인트 전환 조회
     * 포인트 선물 하기
     * 포인트 선물 받기 성공 success boolean으로 ?
     */



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
    public List<PointOutVo> getPointByUser(@RequestHeader("uuid") String uuid) {
        List<PointGetDto> pointListByUser = pointService.getPointByUser(uuid);
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
