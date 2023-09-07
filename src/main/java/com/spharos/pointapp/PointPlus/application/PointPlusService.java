package com.spharos.pointapp.pointplus.application;

import com.spharos.pointapp.pointplus.dto.AttendAddDto;

public interface PointPlusService {

//    출석 적립
    void addPointAttend(AttendAddDto attendAddDto, String uuid);

}
