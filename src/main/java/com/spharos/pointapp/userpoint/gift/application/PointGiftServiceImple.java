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
import com.spharos.pointapp.userpoint.gift.dto.*;
import com.spharos.pointapp.userpoint.gift.infrastructure.PointGiftRepository;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.jpa.impl.JPAQueryFactory;

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

    private final JPAQueryFactory jpaQueryFactory;


    // 1. 포인트 선물 생성
    @Transactional(rollbackFor = Exception.class) // 롤백 설정 추가
    @Override
    public void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException{

        // 본인 확인
        User giverUser = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        // 받는 대상자 확인
        User senderUser = userRepository.findByLoginId(pointGiftCreateDto.getSenderLoginId())
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        // 포인트 비밀번호 없거나 일치하지 않으면 에러
        if (senderUser.getPassword() == null) {
            throw new BaseException(POINT_PASSWORD_RETRIEVE_FAILED);
        } else if (!new BCryptPasswordEncoder()
                .matches(pointGiftCreateDto.getPointPassword(), giverUser.getPassword())) {
            throw new BaseException(POINT_PASSWORD_RETRIEVE_FAILED);
        }

        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid)
                .orElse(null);

        log.info("lastPoint : {}", lastPoint);
        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }


        log.info("setTotalPoint : {}", setTotalPoint);

        // 포인트 차감
        Point point = pointRepository.save(Point.builder()
                .totalPoint(setTotalPoint - pointGiftCreateDto.getGiftPoint())
                .used(true)
                .point(pointGiftCreateDto.getGiftPoint())
                .pointType(PointType.GIFT)
                .build());

        // 포인트 선물 내역
        pointGiftRepository.save(
                PointGift.builder()
                .receiverUuid(senderUser.getUuid())
                .giftPoint(pointGiftCreateDto.getGiftPoint())
                .giftMessage(pointGiftCreateDto.getGiftMessage())
                .giftImage(pointGiftCreateDto.getGiftImage())
                .pointGiftType(PointGiftType.WAIT)
                .point(point)
                .build());

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
    public String getreceiverUser(String userName, String phoneNumber, String uuid) throws BaseException {
        User senderUser = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        log.info("senderUser : {}", senderUser);

        if (senderUser.getName().equals(userName) && senderUser.getPhoneNumber().equals(phoneNumber)) {
            throw new BaseException(GIFT_MYSELF_FAILED);
        }

        return userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber)
                .map(User::getLoginId)
                .orElseThrow(()-> new BaseException(NO_EXIST_USER));
    }

    // 3. 포인트 대기 조회
    @Override
    public PointGiftLastDto getLastGift(String receiverUuid) throws BaseException{

        // 가장 최근 선물 정보
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());
        log.info("pointGiftType {} ", pointGiftType);

        Optional<PointGift> pointGiftSenderUser = pointGiftRepository.findTopByreceiverUuidAndPointGiftTypeOrderByIdAsc(receiverUuid, pointGiftType);
        log.info("pointGiftSenderUser {} ", pointGiftSenderUser);

        // 대기중인 선물포인트가 없다면 null
        if(pointGiftSenderUser.isEmpty()) {
            throw new BaseException(GIFT_NO_HISTORY_FAILED);
        }

        // 보낸 유저 정보 가져오기
        Optional<UserPointList> userPointList = userPointListRepository.findByPointId(pointGiftSenderUser.get().getPoint().getId());

        User giverUser = userRepository.findByUuid(userPointList.get().getUuid())
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        log.info("giverUser {} ", giverUser);

        return PointGiftLastDto.builder()
                .pointGiftId(pointGiftSenderUser.get().getId())
                .giverLoginId(giverUser.getLoginId())
                .giverName(giverUser.getName())
                .point(pointGiftSenderUser.get().getGiftPoint())
                .giftImage(pointGiftSenderUser.get().getGiftImage())
                .giftMessage(pointGiftSenderUser.get().getGiftMessage())
                .createdDate(pointGiftSenderUser.get().getCreateAt().toString())
                .build();
    }

    // 4. 포인트 선물 수락
    @Transactional(rollbackFor = Exception.class) // 롤백 설정 추가
    @Override
    public void updateSuccessGiftPoint(String receiverUuid) throws BaseException {

        // 가장 최근 선물 정보
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());
        log.info("pointGiftType {} ", pointGiftType);

        Optional<PointGift> pointGiftReceiver = pointGiftRepository.findTopByreceiverUuidAndPointGiftTypeOrderByIdAsc(receiverUuid, pointGiftType);
        log.info("pointGiftReceiver {} ", pointGiftReceiver);

        // 유저 포인트 리스트 포인트 아이디로 조회
        UserPointList userPointList = userPointListRepository.findByPointId(pointGiftReceiver.get().getPoint().getId())
                .orElse(null);

        // 포인트 계산
        var setTotalPoint = 0;
        UserPointList lastPoint = userPointList;

        log.info("lastPoint : {}", lastPoint);
        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            log.info("lastPoint.getPoint().getId() : {}", lastPoint.getPoint().getId());
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }
        log.info("setTotalPoint : {}", setTotalPoint);

        Point point = pointRepository.save(Point.builder()
                .totalPoint(setTotalPoint + pointGiftReceiver.get().getGiftPoint())
                .used(false)
                .point(pointGiftReceiver.get().getGiftPoint())
                .pointType(PointType.GIFT)
                .build());

        pointGiftRepository.save(
                PointGift.builder()
                        .id(pointGiftReceiver.get().getId())
                        .receiverUuid(pointGiftReceiver.get().getReceiverUuid())
                        .giftPoint(pointGiftReceiver.get().getGiftPoint())
                        .giftMessage(pointGiftReceiver.get().getGiftMessage())
                        .giftImage(pointGiftReceiver.get().getGiftImage())
                        .point(pointGiftReceiver.get().getPoint())
                        .pointGiftType(PointGiftType.GET)
                        .build()
        );

        userPointListRepository.save(
                UserPointList.builder()
                        .point(point)
                        .uuid(receiverUuid)
                        .pointType(PointType.GIFT)
                        .build()

        );

    }

    // 5. 포인트 선물 거절
    @Override
    public void updateCancelGiftPoint(String receiverUuid) throws BaseException {

        // 가장 최근 선물 정보
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());
        log.info("pointGiftType {} ", pointGiftType);

        Optional<PointGift> pointGiftReceiver = pointGiftRepository.findTopByreceiverUuidAndPointGiftTypeOrderByIdAsc(receiverUuid, pointGiftType);
        log.info("pointGiftReceiver {} ", pointGiftReceiver);

        // 유저 포인트 리스트 포인트 아이디로 조회
        UserPointList userPointList = userPointListRepository.findByPointId(pointGiftReceiver.get().getPoint().getId())
                .orElse(null);

        // 포인트 계산
        var setTotalPoint = 0;
        UserPointList lastPoint = userPointList;

        log.info("lastPoint : {}", lastPoint);
        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            log.info("lastPoint.getPoint().getId() : {}", lastPoint.getPoint().getId());
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        Point point = pointRepository.save(Point.builder()
                .totalPoint(setTotalPoint + pointGiftReceiver.get().getGiftPoint())
                .used(false)
                .point(pointGiftReceiver.get().getGiftPoint())
                .pointType(PointType.GIFT)
                .build());

        pointGiftRepository.save(
                PointGift.builder()
                        .id(pointGiftReceiver.get().getId())
                        .receiverUuid(pointGiftReceiver.get().getReceiverUuid())
                        .giftPoint(pointGiftReceiver.get().getGiftPoint())
                        .giftMessage(pointGiftReceiver.get().getGiftMessage())
                        .giftImage(pointGiftReceiver.get().getGiftImage())
                        .point(pointGiftReceiver.get().getPoint())
                        .pointGiftType(PointGiftType.CANCEL)
                        .build()
        );

        userPointListRepository.save(
                UserPointList.builder()
                        .point(point)
                        .uuid(receiverUuid)
                        .pointType(PointType.GIFT)
                        .build()

        );
    }
}
