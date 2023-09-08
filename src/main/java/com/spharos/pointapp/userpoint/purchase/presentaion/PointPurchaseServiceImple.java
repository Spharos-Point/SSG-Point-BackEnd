package com.spharos.pointapp.userpoint.purchase.presentaion;

import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import com.spharos.pointapp.userpoint.purchase.domain.PointPurchase;
import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseDto;
import com.spharos.pointapp.userpoint.purchase.infrastructure.PointPurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointPurchaseServiceImple implements PointPurchaseService{

    private final PointPurchaseRepository pointPurchaseRepository;
    private final UserPointListRepository userPointListRepository;
    private final BranchRepository branchRepository;

    @Override
    public void purchasePoint(PointPurchaseDto pointPurchaseDto) {

        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(
                pointPurchaseDto.getUuid()
        ).orElseThrow(
                () -> new IllegalArgumentException("has not data.")
        );

        Point point = Point.builder()
                .totalPoint(lastPoint.getPoint().getTotalPoint() + pointPurchaseDto.getPurchasePoint())
                .used(false)
                .point(pointPurchaseDto.getPurchasePoint())
                .pointType(PointType.RECEIPT)
                .build();
        pointPurchaseRepository.save(
                PointPurchase.builder()
                        .purchasePrice(pointPurchaseDto.getPurchasePrice())
                        .purchaseMount(pointPurchaseDto.getPurchaseMount())
                        .point(point)
                        .uuid(pointPurchaseDto.getUuid())
                        .branch(branchRepository.findById(pointPurchaseDto.getBranchId()).get())
                        .build(
        ));
    }
}
