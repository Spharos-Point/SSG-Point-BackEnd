package com.spharos.pointapp.pointgift.application;

import com.spharos.pointapp.pointgift.domain.GiftPoint;
import com.spharos.pointapp.pointgift.dto.*;

public interface PointGiftService {

    /**
     * 선물 포인트
     * 1. 선물 포인트 생성
     * 2. 포인트 선물하기
     * 3. 포인트 선물받기(수락)
     * 4. 포인트 선물받기(거절)
     * 5. 포인트 선물 대기리스트 조회
     * 6. 선물 포인트 조회
     */

    // 1. 선물 포인트 생성

    GiftPoint createGiftPoint(GiftInDto giftInDto);

    // 2. 포인트 선물하기
    void giveGiftPoint(GiftInDto giftInDto, String uuid);



}
