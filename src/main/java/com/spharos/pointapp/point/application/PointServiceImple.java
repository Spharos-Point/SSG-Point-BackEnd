package com.spharos.pointapp.point.application;

import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.domain.PointTypeConverter;
import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.dto.PointGetDto;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import jakarta.persistence.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // 더티체킹 모든 필드 업데이트
//@Transactional(readOnly = true)
public class PointServiceImple implements PointService{

    private final PointRepository pointRepository;
    private final UserPointListRepository userPointListRepository;
    private final UserRepository userRepository;

    /**
     * 1. 토탈 포인트 조회
     * 2. 포인트 계산
     * 3. 포인트 타입별 적립/사용
     * 4. 포인트 적립/사용 조회 (전환, 선물 제회)
     * 5. 포인트 전체 조회
     */

    //  1. 토탈 포인트 조회
    @Override
    public Integer getPointTotalByUser(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        Optional<UserPointList> latestUserPointList = userPointListRepository.findTopByUuidOrderByCreateAtDesc(user.getUuid());
        log.info("latestUserPointList {} ", latestUserPointList);
        if (latestUserPointList.isEmpty() || latestUserPointList.get().equals(0)) {
            return 0;
        } else {
            Point pointTotal = pointRepository.findById(latestUserPointList.get().getPoint().getId()).get();
            return pointTotal.getTotalPoint();
        }
    }

    //  2. 포인트 계산
    @Override
    public Integer calcPointTotal(Boolean used, Integer totalPoint, Integer updatePoint) {
        if (!used) {
            totalPoint += updatePoint;
        } else {
            totalPoint -= updatePoint;
        }
        return totalPoint;
    }

    //  3. 포인트 타입별 적립/사용 (이벤트, 선물, 쿠폰, 출석, 룰렛, 전환, 제휴사, 영수증, 바코드, 소멸
    @Override
    @Convert(converter = PointTypeConverter.class)
    public void createPoint(PointAddDto pointAddDto, String uuid) {

        //  포인트 토탈 계산
        Integer updateTotalPoint = calcPointTotal(pointAddDto.getUsed(),
                getPointTotalByUser(uuid),
                pointAddDto.getPoint());

        //  포인트 타입 설정
        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());

        pointRepository.save(Point.builder()
                .point(pointAddDto.getPoint())
                .totalPoint(updateTotalPoint)
                .used(pointAddDto.getUsed())
                .pointType(pointType)
                .build());
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

