package com.spharos.pointapp.userpoint.trans.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import com.spharos.pointapp.userpoint.trans.domain.PointTrans;
import com.spharos.pointapp.userpoint.trans.dto.PointTransDto;
import com.spharos.pointapp.userpoint.trans.dto.PointTransResDto;
import com.spharos.pointapp.userpoint.trans.infrastructure.PointTransRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointTransServiceImple implements PointTransService {
    private final PointTransRepository pointTransRepository;
    private final ExtraRepository extraRepository;
    private final UserPointListRepository userPointListRepository;
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
                .totalPoint(setTotalPoint + pointTransDto.getTransPoint())
                .point(pointTransDto.getTransPoint())
                .pointType(PointType.AFFILIATE)
                .used(false)
                .build());
        pointTransRepository.save(
                PointTrans.builder()
                        .transPoint(pointTransDto.getTransPoint())
                        .point(point)
                        .extra(extraRepository.findById(pointTransDto.getExtraId()).get())
                        .build());
        userPointListRepository.save(
                UserPointList.builder()
                        .point(point)
                        .uuid(pointTransDto.getUuid())
                        .pointType(PointType.AFFILIATE)
                        .build()
        );
    }

    @Override
    public PointTransResDto getPointTransByPointTransId(Long pointTransId) {
        PointTrans pointTrans = pointTransRepository.findById(pointTransId).orElse(null);
        log.info("{}", pointTrans);
        PointTransResDto pointTransResDto = PointTransResDto.builder()
                .id(pointTrans.getId())
                .transPoint(pointTrans.getTransPoint())
                .point(pointRepository.findById(pointTrans.getPoint().getId()).get())
                .extra(extraRepository.findById(pointTrans.getExtra().getId()).get())
                .build();

        log.info("asdasd {}", pointTransResDto);
        return pointTransResDto;
    }


}