package com.spharos.pointapp.userpoint.trans.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.trans.dto.PointTransDto;
import com.spharos.pointapp.userpoint.trans.dto.PointTransResDto;

public interface PointTransService {

    void transPoint(PointTransDto pointTrnasDto, String uuid) throws BaseException;
    PointTransResDto getPointTransByPointTransId(Long pointTransId) throws BaseException;

}