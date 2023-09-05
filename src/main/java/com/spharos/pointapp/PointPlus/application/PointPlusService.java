package com.spharos.pointapp.PointPlus.application;

import com.spharos.pointapp.PointPlus.dto.AttendAddDto;
import com.spharos.pointapp.PointPlus.dto.RouletteAddDto;

public interface PointPlusService {

//    출석 적립
    void addPointAttend(AttendAddDto attendAddDto, String uuid);

//    룰렛 적립
//    void addPointRoulette(RouletteAddDto rouletteAddDto, String uuid);
}
