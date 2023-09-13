package com.spharos.pointapp.userpoint.purchase.presentaion;

import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import com.spharos.pointapp.userpoint.purchase.domain.PointPurchase;
import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseDto;
import com.spharos.pointapp.userpoint.purchase.dto.PointPurchaseResDto;
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
    private final PointRepository pointRepository;

    @Override
    public void purchasePoint(PointPurchaseDto pointPurchaseDto, String uuid) {

        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid
        ).orElse(null);

        log.info("lastPoint : {}", lastPoint);
//        no value present
        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        log.info("setTotalPoint : {}", setTotalPoint);

        Point point = pointRepository.save(Point.builder()
                .totalPoint(setTotalPoint + pointPurchaseDto.getPurchasePoint())
                .used(false)
                .point(pointPurchaseDto.getPurchasePoint())
                .pointType(PointType.RECEIPT)
                .build());

        pointPurchaseRepository.save(
                PointPurchase.builder()
                        .purchasePrice(pointPurchaseDto.getPurchasePrice())
                        .purchaseMount(pointPurchaseDto.getPurchaseMount())
                        .point(point)
                        .branch(branchRepository.findById(pointPurchaseDto.getBranchId()).get())
                        .build(
        ));

        userPointListRepository.save(
                UserPointList.builder()
                        .point(point)
                        .uuid(uuid)
                        .pointType(PointType.RECEIPT)
                        .build()
        );
    }

    @Override
    public PointPurchaseResDto getPointPurchaseByPointPurchaseId(Long pointPurchaseId) throws BaseException {
        return null;
    }

//    @Override
//    public PointPurchaseResDto getPointPurchaseByPointPurchaseId(Long pointPurchaseId) {
//        PointPurchase pointPurchase = pointPurchaseRepository.findById(pointPurchaseId).orElse(null);
//        PointPurchaseResDto pointPurchaseResDto = PointPurchaseResDto.builder()
//                .id(pointPurchase.getId())
////                .point(pointRepository.findById(pointPurchase.getPoint().getId()).get())
////                .branch(branchRepository.findById(pointPurchase.getBranch().getId()).get())
//                .build();
//        return null;
//    }
}
