package com.spharos.pointapp.user.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spharos.pointapp.config.common.BaseResponseStatus.*;

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
     * 2. 유저 패스워드 변경
     * 3. 유저 패스워드 찾기 및 변경
     * 4. 유저 포인트 패스워드 변경
     * 5. 유저 탈퇴 패스워드 확인
     * 6. 유저 탈퇴 (상태변경)
     * 7. 회원가입 시 로그인 중복 확인
     * 8. 아이디 찾기(유저 이름, 유저 휴대폰 번호로 조회)
     * 9. 비밀번호 찾기(유저 아이디, 유저 이름, 유저 휴대폰 번호 조회)
     * 10. 유저 정보 조회
     *
     */

    //  1. 유저 정보 변경
    @Override
    public void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String uuid) throws BaseException {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        userRepository.save(
                User.builder()
                        .id(user.getId())
                        .loginId(user.getLoginId())
                        .roll(user.getRoll())
                        .address(userUpdateInfoDto.getAddress())
                        .pointPassword(user.getPointPassword())
                        .userName(user.getName())
                        .status(user.getStatus())
                        .uuid(user.getUuid())
                        .phoneNumber(user.getPhoneNumber())
                        .email(userUpdateInfoDto.getEmail())
                        .password(user.getPassword())
                        .build()
        );
    }

    //  2. 유저 패스워드 변경
    @Override
    public void updateUserPwd(String passWord, String newPassword, String uuid) throws BaseException {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()-> new BaseException(NO_EXIST_USER));

        String phoneNum = user.getPhoneNumber();

        // 전화번호의 중간 4자리와 끝 4자리를 추출하여 비밀번호와 비교
        String middleNum = phoneNum.substring(3, 7);
        String lastNum = phoneNum.substring(phoneNum.length() - 4);

        if (new BCryptPasswordEncoder().matches(newPassword, user.getPassword())) {
            throw new BaseException(PASSWORD_SAME_FAILED);
        } else if (newPassword.contains(user.getLoginId())) {
            throw new BaseException(PASSWORD_UPDATE_FAILED);
        } else if (newPassword.contains(middleNum) || newPassword.contains(lastNum)) {
            throw new BaseException(PASSWORD_CONTAIN_NUM_FAILED);
        } else if (new BCryptPasswordEncoder().matches(passWord, user.getPassword())) {
            user.hashPassword(newPassword);
        }

    }

    //  3. 유저 패스워드 찾기 및 변경
    public void searchUserPwd(String loginId, String newPassword) throws BaseException {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        String phoneNum = user.getPhoneNumber();

        // 전화번호의 중간 4자리와 끝 4자리를 추출하여 비밀번호와 비교
        String middleNum = phoneNum.substring(3, 7);
        String lastNum = phoneNum.substring(phoneNum.length() - 4);

        // 패스워드 변경 조건
        if (new BCryptPasswordEncoder().matches(newPassword, user.getPassword())) {
            throw new BaseException(PASSWORD_SAME_FAILED);
        } else if (newPassword.contains(user.getLoginId())) {
            throw new BaseException(PASSWORD_UPDATE_FAILED);
        } else if (newPassword.contains(middleNum) || newPassword.contains(lastNum)) {
            throw new BaseException(PASSWORD_CONTAIN_NUM_FAILED);
        } else {
            user.hashPassword(newPassword);
        }
    }

    //  4. 유저 포인트 패스워드 변경
    @Override
    public void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String uuid) throws BaseException{
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        // 새로운 포인트 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
        user.hashPointPassword(userUpdatePointPwdDto.getNewPointPassword());
    }

    //  5. 유저 탈퇴 패스워드 확인
    @Override
    public void userLeavePwd(String password, String uuid) throws BaseException{
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BaseException(PASSWORD_RETRIEVE_FAILED);
        }
    }

    //  6. 유저 탈퇴(상태변경)
    @Override
    public void userLeaveOnline(String uuid) throws BaseException{
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_USER));

        user.leaveOnlineStatus();
    }

    //  7. 회원가입 시 아이디 중복 확인
    @Override
    public void validateLoginInd(String loginId) throws BaseException{
        if (userRepository.existsByLoginId(loginId)) {
            throw new BaseException(POST_EXISTS_LOGIN_ID);
        }
    }

    //  8. 아이디 찾기(유저 이름, 유저 휴대폰 번호로 조회)
    @Override
    public String getUserLoginIdByNameAndPhoneNumber(String userName, String phoneNumber) throws BaseException {
         return userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber)
                 .map(User::getLoginId)
                 .orElseThrow(()-> new BaseException(NO_EXIST_USER));
    }

    //  9. 비밀번호 찾기(유저 아이디, 유저 이름, 유저 휴대폰 번호 조회)
    @Override
    public void getUserByIdAndNameAndPhoneNumber(String loginId, String userName, String phoneNumber) throws BaseException {
        if (!userRepository.existsByLoginIdAndUserNameAndPhoneNumber(loginId, userName, phoneNumber)) {
            throw new BaseException(PASSWORD_RETRIEVE_FAILED);
        }
    }

    // 10. 유저 정보 조회
    @Override
    public UserGetDto getUserInfo(String uuid) throws BaseException {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()-> new BaseException(NO_EXIST_USER));
        return UserGetDto.builder()
                .loginId(user.getLoginId())
                .userName(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }

}


