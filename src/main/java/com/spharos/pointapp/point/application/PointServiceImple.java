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
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class PointServiceImple implements PointService{

    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     *
     * 1. 포인트 생성
     * 2. 포인트 전체 조회
     * 3. 토탈 포인트 조회
     * 4. 포인트 선물
     * 5. 포인트 선물 적립
     * 6. 포인트 선물 대기 리스트 15일 뒤 취소 됨
     * 7. 포인트 전환
     * 8. 영수중 포인트 적립
     *
     */

    //  1. 포인트 생성
    @Override
    @Convert(converter = PointTypeConverter.class)
    public void createPoint(PointAddDto pointAddDto, String uuid) {

        //  계산을 위해 유저 정보 가져오기
        log.info("PointAddDto is : {}" , pointAddDto);
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new IllegalArgumentException("해당하는 uuid가 없습니다. " + uuid));

        //  포인트 타입 설정
        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());

        pointRepository.save(Point.builder()
                .point(pointAddDto.getPoint())
                .totalPoint(pointAddDto.getPoint())
                .used(pointAddDto.getUsed())
                .pointType(pointType)
                .user(user)
                .build());
    }

    //  2. 포인트 전체 조회

    @Override
    @Convert(converter = PointTypeConverter.class)
    public List<PointGetDto> getPointByUser(String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        List<Point> pointList = pointRepository.findByUser_Uuid(uuid);
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

//    @GetMapping("/point/usablepoint")
//    public ResponseEntity<String> getPointTotal(@RequestHeader("Authorization") String token) {
//
//        Long userId = userService.getUserIdFromToken(token);
//
//        Integer usablePoint = pointService.getUsablePoint(userId);
//
//        UsablePointOut usablePointOut = UsablePointOut.builder()
//                .totalPoint(usablePoint)
//                .build();
//
//        ResponseOut<?> responseOut = ResponseOut.success(usablePointOut);
//        return ResponseEntity.ok(responseOut);
//    }


    @Override
    public List<Point> getAllPoint() {
        return pointRepository.findAll();
    }


}
