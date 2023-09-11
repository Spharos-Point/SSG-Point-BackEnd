package com.spharos.pointapp.userpoint.gift.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import com.spharos.pointapp.userpoint.gift.domain.PointGift;
import com.spharos.pointapp.userpoint.gift.domain.PointGiftType;
import com.spharos.pointapp.userpoint.gift.domain.PointGiftTypeConverter;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftCreateDto;
import com.spharos.pointapp.userpoint.gift.dto.PointGiftLastDto;
import com.spharos.pointapp.userpoint.gift.infrastructure.PointGiftRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.spharos.pointapp.config.common.BaseResponseStatus.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointGiftServiceImple implements PointGiftService {

    /**
     *
     * 1. 포인트 선물 생성
     * 2. 포인트 선물 유저 확인
     * 3. 포인트 대기 조회
     * 4. 포인트 선물 수락
     * 5. 포인트 선물 거절
     *
     */

    private final PointGiftRepository pointGiftRepository;
    private final UserRepository userRepository;
    private final UserPointListRepository userPointListRepository;
    private final PointRepository pointRepository;
    private final ModelMapper modelMapper;

    // 1. 포인트 선물 생성
    @Override
    public void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException{

        // 본인 확인
        User giverUser = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        log.info("giverUser : {}", giverUser);
        log.info("pointGiftCreateDto : {}", pointGiftCreateDto);

        // 받는 대상자 확인
        User senderUser = userRepository.findByLoginId(pointGiftCreateDto.getSenderLoginId())
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        log.info("senderUser : {}", senderUser);
        log.info("giverUser.getPassword() : {}", giverUser.getPassword());
        log.info("pointGiftCreateDto.getPointPassword() : {}", pointGiftCreateDto.getPointPassword());

        // 포인트 비밀번호 없거나 일치하지 않으면 에러
        if (senderUser.getPassword() == null) {
            throw new BaseException(POINT_PASSWORD_RETRIEVE_FAILED);
        } else if (!new BCryptPasswordEncoder()
                .matches(pointGiftCreateDto.getPointPassword(), giverUser.getPassword())) {
            throw new BaseException(POINT_PASSWORD_RETRIEVE_FAILED);
        }


        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid).orElse(null);

        log.info("lastPoint : {}", lastPoint);
        if (lastPoint == null) {
            throw new BaseException(GIFT_FAILED);
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        // 포인트 차감
        Point point = pointRepository.save(Point.builder()
                .totalPoint(setTotalPoint + pointGiftCreateDto.getGiftPoint())
                .used(true)
                .point(pointGiftCreateDto.getGiftPoint())
                .pointType(PointType.GIFT)
                .build());

        // 포인트 선물 내역
        PointGift pointGift = PointGift.builder()
                .giverUuid(giverUser.getUuid())
                .senderUuid(uuid)
                .pointGiftType(PointGiftType.WAIT)
                .point(point)
                .build();
        modelMapper.map(pointGiftCreateDto, pointGift);
        pointGiftRepository.save(pointGift);

        // 유저 포인트 중간 테이블
        userPointListRepository.save(
                UserPointList.builder()
                        .point(point)
                        .uuid(uuid)
                        .pointType(PointType.GIFT)
                        .build()
        );

    }

    // 2. 포인트 선물 유저 확인
    @Override
    public String getSenderUser(String userName, String phoneNumber, String uuid) throws BaseException {
        User senderUser = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        if (senderUser.getName().equals(userName) && senderUser.getPhoneNumber().equals(phoneNumber)) {
            throw new BaseException(GIFT_MYSELF_FAILED);
        }

        return userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber)
                .map(User::getLoginId)
                .orElseThrow(()-> new BaseException(NO_EXIST_USER));
    }

    // 3. 포인트 대기 조회
    @Override
    public PointGiftLastDto getLastGift(String uuid) throws BaseException{

        // 선물 테이블에서 대기 중인 가장 최근 선물 정보만 가져오기
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());
        Optional<PointGift> gift = pointGiftRepository.findTopBySenderUuidAndPointGiftTypeOrderByIdDesc(uuid, pointGiftType);

        // 대기중인 선물 정보가 없는 경우 null을 리턴
        if(gift.isEmpty()) {
            return null;
        }

        PointGift giverUser = gift.get();

        // 보낸 유저 정보 가져오기
        User user = userRepository.findByUuid(giverUser.getGiverUuid())
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        return PointGiftLastDto.builder()
                .giftId(giverUser.getId())
                .senderLoginId(user.getLoginId())
                .senderName(user.getName())
                .point(giverUser.getGiftPoint())
                .giftMessage(giverUser.getGiftMessage())
                .createdDate(giverUser.getCreateAt())
                .build();
    }


//
//    // 4. 포인트 선물 수락
//    @Override
//    public void updateGiftSuccess(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException {
//
//        var setTotalPoint = 0;
//        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid)
//                .orElse(null);
//
//        log.info("lastPoint : {}", lastPoint);
//        if (lastPoint == null) {
//            setTotalPoint = 0;
//        } else {
//            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
//        }
//
//        // 포인트 증가
//        Point point = pointRepository.save(Point.builder()
//                .totalPoint(setTotalPoint + pointGiftDto.getGiftPoint())
//                .used(false)
//                .point(pointGiftDto.getGiftPoint())
//                .pointType(PointType.GIFT)
//                .build());
//
//        userPointListRepository.save(
//                UserPointList.builder()
//                        .point(point)
//                        .uuid(pointPurchaseDto.getUuid())
//                        .pointType(PointType.RECEIPT)
//                        .build()
//        );
//        Point point = pointRepository.save(Point.builder()
//                .totalPoint(setTotalPoint + pointPurchaseDto.getPurchasePoint())
//                .used(false)
//                .point(pointPurchaseDto.getPurchasePoint())
//                .pointType(PointType.RECEIPT)
//                .build());
//
//    }


}
