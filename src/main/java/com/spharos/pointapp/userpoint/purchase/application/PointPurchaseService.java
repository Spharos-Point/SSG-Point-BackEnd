package com.spharos.pointapp.userpoint.purchase.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseDto;

import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseResDto;
import java.util.List;


public interface PointPurchaseService {

    void purchasePoint(PointPurchaseDto pointPurchaseDto, String uuid) throws BaseException;
    List<PointPurchaseResDto> getPointPurchaseByUuid(String uuid) throws BaseException;
}
