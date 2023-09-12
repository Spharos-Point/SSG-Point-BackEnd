package com.spharos.pointapp.userpoint.trans.presentation;

import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import com.spharos.pointapp.userpoint.trans.domain.PointTrans;
import com.spharos.pointapp.userpoint.trans.dto.PointTransDto;
import com.spharos.pointapp.userpoint.trans.infrastructure.PointTransRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointTransServiceImple implements PointTransService {

    private final PointTransRepository pointTransRepository;
    private final UserPointListRepository userPointListRepository;
    private final BranchRepository branchRepository;
    private final PointRepository pointRepository;

    @Override
    public void transPoint(PointTransDto pointTransDto, String uuid) throws BaseException {

        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(
                pointTransDto.getUuid()
        ).orElse(null);

        log.info("lastPoint : {}", lastPoint);

        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        log.info("setTotalPoint : {}", setTotalPoint);

        Point point = pointRepository.save(Point.builder()
                        .totalPoint(setTotalPoint + pointTransDto.getTransMount())
                        .used(false)
                        .point(pointTransDto.getTransMount())
                .build());

        pointTransRepository.save(
                PointTrans.builder()
                        .trnasPoint(pointTransDto.getTransPoint())
                        .transMount(pointTransDto.getTransMount())
                        .point(point)
                        .branch(branchRepository.findById(pointTransDto.getBranchId()).get())
                        .build(
                        ));

        userPointListRepository.save(
                UserPointList.builder()
                        .point(point)
                        .uuid(pointTransDto.getUuid())
                        .pointType(PointType.RECEIPT)
                        .build()
        );
    }
}