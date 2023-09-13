package com.spharos.pointapp.userpoint.gift.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftCreateDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftHistoryDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftLastDto;

import java.util.List;

public interface PointGiftService {
    void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException;
    String getReceiverUser(String userName, String phoneNumber, String uuid) throws BaseException;
    PointGiftLastDto getLastGift(String receiverUuid) throws BaseException;
    void updateSuccessGiftPoint(String receiverUuid) throws BaseException;
    void updateCancelGiftPoint(String receiverUuid) throws BaseException;
//    List<PointGiftHistoryDto> getGiftHistory(String uuid) throws BaseException;

}
