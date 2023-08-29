package com.spharos.pointapp.pointcard.application;

import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PointCardService {

    // 포인트 카드 생성
    void createPointCard(PointCardCreateDto pointCardCreateDto, String uuid);
    // 포인트 카드 목록
    List<PointCardOutDto> getPointcardByUser();
}
