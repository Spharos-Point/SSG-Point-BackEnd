package com.spharos.pointapp.userpoint.gift.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftAcceptDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftCreateDto;

public interface PointGiftService {
    void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException;
    String getSenderUser(String userName, String phoneNumber, String uuid) throws BaseException;
//    void updateGiftSuccess(PointGiftAcceptDto pointGiftAcceptDto, String uuid) throws BaseException;

}
