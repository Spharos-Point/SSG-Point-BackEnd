package com.spharos.pointapp.userpoint.gift.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftCreateDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftLastDto;

public interface PointGiftService {
    void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException;
    String getreceiverUser(String userName, String phoneNumber, String uuid) throws BaseException;
    PointGiftLastDto getLastGift(String receiverUuid) throws BaseException;
    void updateSuccessGiftPoint(String receiverUuid) throws BaseException;
    void updateCancelGiftPoint(String receiverUuid) throws BaseException;

    // 4. 포인트 선물 거절
}
