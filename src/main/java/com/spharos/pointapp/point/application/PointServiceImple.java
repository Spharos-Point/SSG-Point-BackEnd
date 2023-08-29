package com.spharos.pointapp.point.application;

import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.domain.PointTypeConverter;
import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.dto.PointGetDto;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements PointService{

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    /**
     * 포인트
     * 1. 포인트 생성
     * 2. 포인트 전체 조회
     * 3. 포인트 선물
     * 4. 포인트 선물 적립
     * 5. 포인트 선물 대기 리스트 15일 뒤 취소 됨
     * 6. 포인트 전환
     * 7. 영수중 포인트 적립
     */

//    포인트 생성
    @Override
    @Convert(converter = PointTypeConverter.class)
    public void createPoint(PointAddDto pointAddDto) {

//      계산을 위해 유저 정보 가져오기

        User getUser = userRepository.findByLoginId(pointAddDto.getLoginId()).get();
        log.info("user is : {}" , getUser);
        if(getUser == null){
            log.info("user is null");
            return;
        }
//      포인트 타입 설정
        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());


        pointRepository.save(Point.builder()
                .point(pointAddDto.getPoint())
                .totalPoint(pointAddDto.getPoint())
                .pointType(pointType)
                .user(getUser)
                .used(pointAddDto.getUsed())
                .build());
    }

    @Override
    @Convert(converter = PointTypeConverter.class)
    public List<PointGetDto> getPointByUser(Long userId) {
        List<Point> pointList = pointRepository.findByUserId(userId);
        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
                    PointType pointType = new PointTypeConverter().convertToEntityAttribute(point.getPointType().getCode());
                    return PointGetDto.builder()
                            .pointType(pointType.getValue())
                            .point(point.getPoint())
                            .used(point.getUsed())
                            .build();
                }
        ).toList();
        log.info("pointList is : {}" , pointList);
        return pointGetDtoList;
    }

    @Override
    public List<Point> getAllPoint() {
        return pointRepository.findAll();
    }


}
