package com.spharos.pointapp.userpoint.pointList.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.userpoint.gift.infrastructure.PointGiftRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.dto.UserPointListResDto;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import com.spharos.pointapp.userpoint.purchase.infrastructure.PointPurchaseRepository;
import com.spharos.pointapp.userpoint.trans.infrastructure.PointTransRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPointListServiceImple implements UserPointListService {

    private final UserPointListRepository userPointListRepository;
    private final PointPurchaseRepository pointPurchaseRepository;
    private final PointGiftRepository pointGiftRepository;
    private final PointTransRepository pointTransRepository;
    private final PointRepository pointRepository;

    @Override
    public List<UserPointListResDto> getUserPointListByUuid(String uuid) throws BaseException {

        List<UserPointList> userPointList = userPointListRepository.findByUuid(uuid);
        List<UserPointListResDto> userPointListResDtoList = userPointList.stream().map(userPoint -> {
            Long pointTypeId = null;
            if(userPoint.getPointType().getCode() == "RE") {
               pointTypeId =  pointPurchaseRepository.findByPointId(
                       userPoint.getPoint().getId()).orElse(null).getId();
            } else if(userPoint.getPointType().getCode() == "GI") {
                pointTypeId =  pointGiftRepository.findByPointId(
                        userPoint.getPoint().getId()).orElse(null).getId();
            } else if(userPoint.getPointType().getCode() == "TR") {
                pointTypeId =  pointTransRepository.findByPointId(
                        userPoint.getPoint().getId()).orElse(null).getId();
            }
            return UserPointListResDto.builder()
                    .id(userPoint.getId())
                    .pointId(pointRepository.findById(userPoint.getPoint().getId()).orElse(null).getId())
                    .pointType(userPoint.getPointType().getValue())
                    .pointTypeById(pointTypeId)
                    .createAt(userPoint.getCreateAt().toString())
                    .build();
        }).toList();
        log.info("userPointList : {}", userPointListResDtoList);
        return userPointListResDtoList;
    }
}