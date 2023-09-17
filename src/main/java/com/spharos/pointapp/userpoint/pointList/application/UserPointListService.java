package com.spharos.pointapp.userpoint.pointList.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.userpoint.pointList.dto.UserPointListResDto;

import java.util.List;

public interface UserPointListService {

    List<UserPointListResDto> getUserPointListByUuid(String uuid) throws BaseException;
    Integer getTotalPointByUuid(String uuid) throws BaseException;

    List<UserPointListResDto> getUserPointListByUuidSortByPointTypeAndRange(String uuid, String pointType, String range ) throws BaseException;

}
