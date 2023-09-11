package com.spharos.pointapp.userpoint.gift.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftAcceptDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftCreateDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftLastDto;

public interface PointGiftService {
    void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException;
    String getSenderUser(String userName, String phoneNumber, String uuid) throws BaseException;
    PointGiftLastDto getLastGift(String senderUuid) throws BaseException;
    void updateGiftPointSuccess(String senderUuid) throws BaseException;
    void updateGiftPointCancel(String senderUuid) throws BaseException;

}
