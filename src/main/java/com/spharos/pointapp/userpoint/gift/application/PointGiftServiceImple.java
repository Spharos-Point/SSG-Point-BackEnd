package com.spharos.pointapp.userpoint.gift.application;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.infrastructure.PointRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import com.spharos.pointapp.userpoint.gift.domain.PointGift;
import com.spharos.pointapp.userpoint.gift.domain.PointGiftType;
import com.spharos.pointapp.userpoint.gift.domain.PointGiftTypeConverter;
import com.spharos.pointapp.userpoint.gift.domain.QPointGift;
import com.spharos.pointapp.userpoint.gift.dto.*;
import com.spharos.pointapp.userpoint.gift.infrastructure.PointGiftRepository;
import com.spharos.pointapp.userpoint.pointList.domain.QUserPointList;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * 6. 포인트 선물 조회
     *
     */

    private final PointGiftRepository pointGiftRepository;
    private final UserRepository userRepository;
    private final UserPointListRepository userPointListRepository;
    private final PointRepository pointRepository;
    private final JPAQueryFactory query;

    // 1. 포인트 선물 생성
    @Transactional(rollbackFor = Exception.class) // 롤백 설정 추가
    @Override
    public void giftPoint(PointGiftCreateDto pointGiftCreateDto, String uuid) throws BaseException{

        // 본인 확인
        User giverUser = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        // 받는 대상자 확인
        User receiverUser = userRepository.findByLoginId(pointGiftCreateDto.getReceiverLoginId())
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        // 포인트 비밀번호 없거나 일치하지 않으면 에러
        if (receiverUser.getPassword() == null) {
            throw new BaseException(POINT_PASSWORD_RETRIEVE_FAILED);
        } else if (!new BCryptPasswordEncoder()
                .matches(pointGiftCreateDto.getPointPassword(), giverUser.getPassword())) {
            throw new BaseException(POINT_PASSWORD_RETRIEVE_FAILED);
        }

        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(uuid)
                .orElse(null);

        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        // 포인트 선물 가능 금액 확인
        if (setTotalPoint == 0) {
            throw new BaseException(NO_POINT_FAILED);
        }

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
                .receiverUuid(receiverUser.getUuid())
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
    public String getReceiverUser(String userName, String phoneNumber, String uuid) throws BaseException {
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
    public PointGiftLastDto getLastGift(String receiverUuid) throws BaseException{

        // 가장 최근 선물 정보
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());

        Optional<PointGift> pointGiftReceiver = pointGiftRepository.findTopByReceiverUuidAndPointGiftTypeOrderByIdDesc(receiverUuid, pointGiftType);

        // 대기중인 선물포인트가 없다면 null
        if(pointGiftReceiver.isEmpty()) {
            return null;
        }

        // 보낸 유저 정보 가져오기
        UserPointList userPointList = userPointListRepository.findByPointId(pointGiftReceiver.get().getPoint().getId())
                .orElseThrow(() -> new BaseException(NO_USER_POINT_LIST_HISTORY_FAILED));
        User giverUser = userRepository.findByUuid(userPointList.getUuid())
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        return PointGiftLastDto.builder()
                .pointGiftId(pointGiftReceiver.get().getId())
                .giverLoginId(giverUser.getLoginId())
                .giverName(giverUser.getName())
                .point(pointGiftReceiver.get().getGiftPoint())
                .giftImage(pointGiftReceiver.get().getGiftImage())
                .giftMessage(pointGiftReceiver.get().getGiftMessage())
                .createdDate(pointGiftReceiver.get().getCreateAt().toString())
                .build();
    }

    // 4. 포인트 선물 수락
    @Transactional(rollbackFor = Exception.class) // 롤백 설정 추가
    @Override
    public void updateSuccessGiftPoint(String receiverUuid) throws BaseException {

        // 가장 최근 선물 정보
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());

        PointGift pointGiftReceiver = pointGiftRepository.findTopByReceiverUuidAndPointGiftTypeOrderByIdDesc(receiverUuid, pointGiftType)
                        .orElseThrow(() -> new BaseException(GIFT_NO_HISTORY_FAILED));

        // 보낸 유저 정보 가져오기
        UserPointList userPointList = userPointListRepository.findByPointId(pointGiftReceiver.getPoint().getId())
                .orElseThrow(() -> new BaseException(NO_USER_POINT_LIST_HISTORY_FAILED));

        // 포인트 계산
        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(receiverUuid)
                .orElse(null);

        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        pointRepository.save(Point.builder()
            .totalPoint(setTotalPoint + pointGiftReceiver.getGiftPoint())
            .used(false)
            .point(pointGiftReceiver.getGiftPoint())
            .pointType(PointType.GIFT)
            .build());

        pointGiftRepository.save(
                PointGift.builder()
                        .id(pointGiftReceiver.getId())
                        .receiverUuid(receiverUuid)
                        .giftPoint(pointGiftReceiver.getGiftPoint())
                        .giftMessage(pointGiftReceiver.getGiftMessage())
                        .giftImage(pointGiftReceiver.getGiftImage())
                        .point(pointGiftReceiver.getPoint())
                        .pointGiftType(PointGiftType.ACCEPT)
                        .build()
        );
    }

    // 5. 포인트 선물 거절
    @Transactional(rollbackFor = Exception.class) // 롤백 설정 추가
    @Override
    public void updateCancelGiftPoint(String receiverUuid) throws BaseException {

        // 가장 최근 선물 정보
        PointGiftType pointGiftType = new PointGiftTypeConverter().convertToEntityAttribute(PointGiftType.WAIT.getCode());

        PointGift pointGiftReceiver = pointGiftRepository.findTopByReceiverUuidAndPointGiftTypeOrderByIdDesc(receiverUuid, pointGiftType)
                .orElseThrow(() -> new BaseException(GIFT_NO_HISTORY_FAILED));

        // 보낸 유저 정보 가져오기
        UserPointList userPointList = userPointListRepository.findByPointId(pointGiftReceiver.getPoint().getId())
                .orElseThrow(() -> new BaseException(NO_USER_POINT_LIST_HISTORY_FAILED));

        // 포인트 계산
        var setTotalPoint = 0;
        UserPointList lastPoint = userPointListRepository.findTopByUuidOrderByCreateAtDesc(receiverUuid)
                .orElse(null);

        if (lastPoint == null) {
            setTotalPoint = 0;
        } else {
            setTotalPoint = pointRepository.findById(lastPoint.getPoint().getId()).get().getTotalPoint();
        }

        pointRepository.save(Point.builder()
            .totalPoint(setTotalPoint + pointGiftReceiver.getGiftPoint())
            .used(false)
            .point(pointGiftReceiver.getGiftPoint())
            .pointType(PointType.GIFT)
            .build());

        // uuid 위치 변경 및 타입 변경
        pointGiftRepository.save(
                PointGift.builder()
                        .id(pointGiftReceiver.getId())
                        .receiverUuid(userPointList.getUuid())
                        .giftPoint(pointGiftReceiver.getGiftPoint())
                        .giftMessage(pointGiftReceiver.getGiftMessage())
                        .giftImage(pointGiftReceiver.getGiftImage())
                        .point(pointGiftReceiver.getPoint())
                        .pointGiftType(PointGiftType.REFUSE)
                        .build()
        );

        // 유저 포인트 중간 테이블
        userPointListRepository.save(
                UserPointList.builder()
                        .id(userPointList.getId())
                        .point(pointGiftReceiver.getPoint())
                        .uuid(receiverUuid)
                        .pointType(PointType.GIFT)
                        .build()
        );

    }

//    // 6. 포인트 선물 조회
//    @Transactional(rollbackFor = Exception.class) // 롤백 설정 추가
//    @Override
//    public List<PointGiftHistoryDto> getGiftHistory(String uuid) throws BaseException {
//        String receiverUuid = uuid;
//
//
//        /**
//         *
//         포인트 선물 받은 기록은 포인트 선물에서 receiver uuid와 ACCEPT로 찾음
//
//         포인트 선물 거절과 보낸거는 유저 포인트 리스트에서
//         giver uuid를 가지고 포인트 타입을 찾고
//         포인트 선물에서 해당 포인트 타입에서 WAIT와 REFUSE 리스트 가져오기
//
//         */
//        PointGiftType AcceptType = PointGiftType.ACCEPT;
//        PointGiftType RefuseType = PointGiftType.REFUSE;
//
//        Predicate predicate = PointGift.receiverUuid.eq(receiverUuid)
//                .and(PointGift.pointGiftType.in(pointGiftTypes));
//
//
//        List<PointGift> acceptedOrRefusedGifts = pointGiftRepository.findByToUserUuidAndGiftStatusIn(toUserUuid,
//                Arrays.asList(PointGiftStatus.WAIT, PointGiftStatus.REFUSE));
//
//        if (acceptedOrRefusedGifts.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        List<PointGiftEntityDto> dtoPointGifts = acceptedOrRefusedGifts.stream()
//                .map(pointGift ->
//                        modelMapper.map(pointGift, PointGiftEntityDto.class))
//                .collect(Collectors.toList());

//        PointTrans pointTrans = pointGiftRepository.findById(pointTransId).orElse(null);
//        PointTransResDto pointTransResDto = PointTransResDto.builder()
//                .id(pointTrans.getId())
//                .point(pointRepository.findById(pointTrans.getPoint().getId()).get())
//                .extra(extraRepository.findById(pointTrans.getExtra().getId()).get())
//                .build();
//        return pointTransResDto;


}