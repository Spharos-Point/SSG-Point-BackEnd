package com.spharos.pointapp.userpoint.pointList.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.userpoint.gift.infrastructure.PointGiftRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.dto.UserPointListResDto;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import com.spharos.pointapp.userpoint.pointList.specification.UserPointListSpecification;
import com.spharos.pointapp.userpoint.purchase.infrastructure.PointPurchaseRepository;
import com.spharos.pointapp.userpoint.trans.infrastructure.PointTransRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Integer getTotalPointByUuid(String uuid) throws BaseException {
        Optional<UserPointList> userPointList = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid);

        if (userPointList.isPresent()) {
            Point userLastPoint = pointRepository.findById(userPointList.get().getPoint().getId()).get();
            return userLastPoint.getTotalPoint();
        } else {
            return 0;
        }
    }

    @Override
    public List<UserPointListResDto> getUserPointListByUuidSortByPointTypeAndRange(String uuid, String pointType, String range) throws BaseException {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = range == "all" ? null : range == "1" ? now.minusDays(7) : range == "3" ? now.minusMonths(1) : range == "6" ? now.minusMonths(6) : null;
        LocalDateTime endDate = now;

        PointType getPointType = pointType == "all" ? null : pointType == "RE,RE_USED" ? PointType.RECEIPT : pointType == "GI" ? PointType.GIFT : pointType == "TR" ? PointType.TRANCE : null;

        Specification<UserPointList> spec = Specification.where(UserPointListSpecification.withUuid(uuid))
                .and(UserPointListSpecification.withPointType(getPointType))
                .and(UserPointListSpecification.withCreateAt(startDate, endDate));

        List<UserPointList> userPointList = userPointListRepository.findAll(spec);
        log.info("userPointList : {}", userPointList);

        List<UserPointListResDto> pointListResDtos = userPointList.stream().map(userPoint -> {
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
        return pointListResDtos;
    }
}
