package com.spharos.pointapp.userpoint.purchase.application;


import com.spharos.pointapp.brand.domain.Branch;
import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.brand.infrastructure.BrandRepository;
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


import java.util.List;
import java.util.Optional;

import static com.spharos.pointapp.config.common.BaseResponseStatus.NO_POINT_HISTORY;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointPurchaseServiceImple implements PointPurchaseService{


    private final PointPurchaseRepository pointPurchaseRepository;
    private final UserPointListRepository userPointListRepository;
    private final BranchRepository branchRepository;
    private final BrandRepository brandRepository;
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
    public List<PointPurchaseResDto> getPointPurchaseByUuid(String uuid){
        List<UserPointList> userPointList = userPointListRepository.findByUuid(uuid);

        List<PointPurchaseResDto> PointPurchaseResDtoList = userPointList.stream().map(userPoint -> {
            if(userPoint.getPointType().getCode() == "RE") {
                PointPurchase pointPurchase = pointPurchaseRepository.findByPointId(userPoint.getPoint().getId()).orElse(null);

                if (pointPurchase != null) {

                    Optional<Point> point = pointRepository.findById(
                            userPoint.getPoint().getId());

                    Branch branch = branchRepository.findById(pointPurchase.getBranch().getId()).orElse(null);
                    assert branch != null;
                    assert point.isPresent();
                    return PointPurchaseResDto.builder()
                            .id(userPoint.getId())
                            .pointId(pointRepository.findById(userPoint.getPoint().getId()).orElse(null).getId())
                            .branchName(branch.getBranchName())
                            .brandName(brandRepository.findById(branch.getBrand().getId()).orElse(null).getBrandName())
                            .pointType("RE")
                            .used(point.get().getUsed())
                            .purchaseMount(pointPurchase.getPurchaseMount())
                            .purchasePrice(pointPurchase.getPurchasePrice())
                            .purchasePoint(point.get().getPoint())
                            .purchaseCreateAt(pointPurchase.getCreateAt().toString())
                            .build();
                }
            }
            return null;
        }).toList();
        return PointPurchaseResDtoList;

    }

    @Override
    public PointPurchaseResDto getPointPurchaseById(Long pointPurchaseId) {
        PointPurchase pointPurchase = pointPurchaseRepository.findById(pointPurchaseId).orElse(null);
        if (pointPurchase != null) {
            Optional<Point> point = pointRepository.findById(pointPurchase.getPoint().getId());
            Branch branch = branchRepository.findById(pointPurchase.getBranch().getId()).orElse(null);
            assert branch != null;
            assert point.isPresent();
            return PointPurchaseResDto.builder()
                    .id(pointPurchase.getId())
                    .pointId(pointRepository.findById(pointPurchase.getPoint().getId()).orElse(null).getId())
                    .branchName(branch.getBranchName())
                    .brandName(brandRepository.findById(branch.getBrand().getId()).orElse(null).getBrandName())
                    .pointType("RE")
                    .used(point.get().getUsed())
                    .purchaseMount(pointPurchase.getPurchaseMount())
                    .purchasePrice(pointPurchase.getPurchasePrice())
                    .purchasePoint(point.get().getPoint())
                    .purchaseCreateAt(pointPurchase.getCreateAt().toString())
                    .build();
        }
        return null;
    }

}
