package com.spharos.pointapp.userpoint.purchase.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseDto;

public interface PointPurchaseService {

    void purchasePoint(PointPurchaseDto pointPurchaseDto, String uuid) throws BaseException;

}
