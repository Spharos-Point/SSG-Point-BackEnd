package com.spharos.pointapp.point.application;

import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // 더티체킹 모든 필드 업데이트
//@Transactional(readOnly = true)
public class PointServiceImple implements PointService{

    private final UserPointListRepository userPointListRepository;

    /**
     * 1. 토탈 포인트 조회
     * 2. 포인트 전체 조회
     */

    //  1. 토탈 포인트 조회
    @Override
    public Integer getPointTotalByUser(String uuid) {

        // 사용자의 가장 최근 포인트 기록을 찾습니다.
        Optional<UserPointList> userPointList = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid);

        if (userPointList.isPresent()) {
            UserPointList latestUserPointList = userPointList.get();

            // UserPointList에서 포인트 정보를 가져옵니다.
            Point point = latestUserPointList.getPoint();

            // 포인트 총합을 반환합니다.
            return point.getTotalPoint();
        } else {
            // 최근 포인트 기록이 없는 경우 0을 반환합니다.
            return 0;
        }
    }



//    //  전체조회 강사님 코드
//    @Override
//    @Convert(converter = PointTypeConverter.class)
//    public List<PointGetDto> getPointByUser(String uuid) {
//        List<Point> pointList = pointRepository.findByUuid(uuid);
//        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
//                    PointType pointType = new PointTypeConverter().convertToEntityAttribute(point.getPointType().getCode());
//                    return PointGetDto.builder()
//                            .pointType(pointType.getValue())
//                            .point(point.getPoint())
//                            .used(point.getUsed())
//                            .build();
//                }
//        ).toList();
//        log.info("pointList is : {}" , pointList);
//        return pointGetDtoList;
//    }
//
//    @Override
//    public List<Point> getAllPoint() {
//        return pointRepository.findAll();
//    }


}

