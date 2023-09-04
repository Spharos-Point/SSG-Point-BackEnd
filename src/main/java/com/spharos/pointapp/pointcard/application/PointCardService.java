package com.spharos.pointapp.pointcard.application;

import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
import java.util.List;

public interface PointCardService {

    //    1. 포인트 카드 생성
    void createPointCard(PointCardCreateDto pointCardCreateDto);

//   2. 포인트 카드 조회
    List<PointCardOutDto> getPointCardByUser(String uuid);

}
