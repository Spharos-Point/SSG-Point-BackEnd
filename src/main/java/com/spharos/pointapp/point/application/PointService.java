package com.spharos.pointapp.point.application;

import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.dto.PointGetDto;

import java.util.List;

public interface PointService {
    void createPoint(PointAddDto pointAddDto, String uuid);
    List<PointGetDto> getPointByUser(String uuid);
    List<Point> getAllPoint();
}
