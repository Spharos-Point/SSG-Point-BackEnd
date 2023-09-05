package com.spharos.pointapp.point.application;

import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.dto.PointGetDto;

import java.util.List;
import java.util.Optional;

public interface PointService {

    /**
     * 1. 토탈 포인트 조회
     * 2. 포인트 계산
     * 3. 포인트 일반 적립/사용
     * 4. 포인트 전체 조회
     * 5. 포인트 적립 조회
     * 6. 포인트 사용 조회
     */


    //  1. 토탈 포인트 조회
    Integer getPointTotalByUser(String uuid);

    //  2. 포인트 계산
    Integer calcPointTotal(Boolean used, Integer totalPoint, Integer updatePoint);

    //  3. 포인트 생성
    void createPoint(PointAddDto pointAddDto, String uuid);

    //  4. 포인트 전체 조회
    List<PointGetDto> getPointByUser(String uuid);


//    List<Point> getAllPoint();
}
