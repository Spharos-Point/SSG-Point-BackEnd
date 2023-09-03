package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // 더티체킹 모든 필드 업데이트
//@Transactional(readOnly = true)
public class UserServiceImple implements UserService{
    private final UserRepository userRepository;

    /**
     *
     * 1. 유저 정보 변경
     * 2. 윺저 패스워드 변경
     * 3. 유저 포인트 패스워드 변경
     * 4. 유저 탈퇴 패스워드 확인
     * 5. 유저 탈퇴 (상태변경)
     * 6. 로그인 id 조회
     * 7.
     */

    //  1. 유저 정보 변경
    @Override
    public void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException("해당하는 uuid가 없습니다. " + uuid));
        userRepository.save(
                User.builder()
                        .id(user.getId())
                        .loginId(user.getLoginId())
                        .roll(user.getRoll())
                        .address(userUpdateInfoDto.getAddress())
                        .pointPassword(user.getPointPassword())
                        .userName(user.getUsername())
                        .status(user.getStatus())
                        .uuid(user.getUuid())
                        .phoneNumber(user.getPhoneNumber())
                        .email(userUpdateInfoDto.getEmail())
                        .password(user.getPassword())
                        .build()
        );
        log.info("{}", user);
    }

    //  2. 유저 패스워드 변경
    @Override
    public void updateUserPwd(UserUpdatePwdDto updatePwDto, String uuid)  {
        User user = userRepository.findByUuid(uuid).get();
        // 사용자가 입력한 현재 패스워드와 DB에 저장된 시큐리티 패스워드를 비교
        if (!new BCryptPasswordEncoder().matches(updatePwDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("패스워드가 올바르지 않습니다.");
        }

        // 새로운 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
        user.hashPassword(updatePwDto.getNewPassword());

    }

    //  3. 유저 포인트 패스워드 변경
    @Override
    public void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException ("해당하는 uuid가 없습니다. " + uuid));
        // 새로운 포인트 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
        user.hashPointPassword(userUpdatePointPwdDto.getNewPointPassword());
    }

    //  4. 유저 탈퇴 패스워드 확인
    @Override
    public Boolean userLeavePwd(String password, String uuid) {
        User user = userRepository.findByUuid(uuid).get();
        log.info("user : {}",user );
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            return false;
        } else {
            return true;
        }
    }

    //  5. 유저 탈퇴(상태변경)
    @Override
    public void userLeaveOnline(String uuid) {
        Optional<User> user = userRepository.findByUuid(uuid);
        user.get().leaveOnlineStatus();
    }

    //  6. 회원가입 시 로그인 중복 확인
    @Override
    public Boolean validateLoginInd(String loginId) {
        return !userRepository.findByLoginId(loginId).isPresent();
    }

    //  7. 유저 휴대폰 번호, 유저 네임 으로 조회
    @Override
    public String getUserByNameAndPhoneNumber(String userName, String phoneNumber) {
        // findByUserNameAndPhoneNumber 매개변수 순서 엔티티 순서랑 관련있음 ex) userName가 먼저 phoneNumber보다 엔티티에 선언됨
        log.info("findByUserNameAndPhoneNumberuserName : {} ",userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber));

        log.info("user : {}",userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber).map(User::getLoginId));

        return userRepository
                .findByUserNameAndPhoneNumber(userName, phoneNumber)
                .map(User::getLoginId)
                .orElse(null);
    }


////    강사님 코드 로그인 id 참고
//    @Override
//    public UserGetDto getUserByLoginId(String loginId) {
//
//        User user = userRepository.findByLoginId(loginId).get();
//        log.info("user is : {}" , user);
//        UserGetDto userGetDto = UserGetDto.builder()
//                .loginId(user.getLoginId())
//                .userName(user.getUsername())
//                .email(user.getEmail())
//                .phone(user.getPhone())
//                .address(user.getAddress())
//                .build();
//        return userGetDto;
//    }
}
