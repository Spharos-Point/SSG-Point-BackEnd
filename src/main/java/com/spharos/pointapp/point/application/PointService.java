package com.spharos.pointapp.point.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.dto.PointGetDto;

public interface PointService {

    /**
     * 1. 토탈 포인트 조회
     * 2. 포인트 전체 조회
     */

    //  1. 토탈 포인트 조회
    Integer getPointTotalByUser(String uuid) throws BaseException;
    PointGetDto getPointByPointId(Long pointId) throws BaseException;

}
