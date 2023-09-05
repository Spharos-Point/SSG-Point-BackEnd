package com.spharos.pointapp.PointPlus.application;

import com.spharos.pointapp.PointPlus.dto.AttendAddDto;

public interface PointPlusService {

//    출석 적립
    void addPointAttend(AttendAddDto attendAddDto, String uuid);

}
