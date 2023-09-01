package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

//    1. 유저 정보 변경
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
                        .name(user.getName())
                        .status(user.getStatus())
                        .uuid(user.getUuid())
                        .phone(user.getPhone())
                        .email(userUpdateInfoDto.getEmail())
                        .password(user.getPassword())
                        .build()
        );
        log.info("{}", user);
    }

//    2. 유저 패스워드 변경
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

//    3. 유저 포인트 패스워드 변경
    @Override
    public void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException ("해당하는 uuid가 없습니다. " + uuid));
        // 새로운 포인트 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
        user.hashPointPassword(userUpdatePointPwdDto.getNewPointPassword());
    }

//    4. 유저 탈퇴 패스워드 확인
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

//    5. 유저 탈퇴(상태변경)
    @Override
    public void userLeaveOnline(String uuid) {
        Optional<User> user = userRepository.findByUuid(uuid);
        user.get().leaveOnlineStatus();
    }

//    //  6.  회원가입 시 로그인 중복 확인
//
//    @Override
//    public User getUserByUUID(String userUUID) {
//        User user = userRepository.findUserByUserUUID(userUUID)
//                .orElseThrow(()->new NoSuchElementException());
//        return user;
//    }
//    @Override
//    public void validateLoginInd(String loginId) {
//        User user = userRepository.findByLoginId(loginId)
//                .orElseThrow(() -> new NoSuchElementException("이미 존재하는 아이디입니다."));
//
//    }

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
