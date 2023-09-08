package com.spharos.pointapp.userpoint.purchase.application;

import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseDto;
import com.spharos.pointapp.userpoint.purchase.presentaion.PointPurchaseService;
import com.spharos.pointapp.userpoint.purchase.vo.PointPurchaseAddRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PointPurchaseController {

    private final PointPurchaseService pointPurchaseService;
    private final ModelMapper modelMapper;

    @PostMapping("/point-purchase")
    public void purchasePoint(
            @RequestBody PointPurchaseAddRequest pointPurchaseAddRequest
            ) {
        log.info("purchasePoint");
        pointPurchaseService.purchasePoint(
                modelMapper.map(pointPurchaseAddRequest, PointPurchaseDto.class)
        );
    }

}
