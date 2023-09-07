package com.spharos.pointapp.user.application;

import com.spharos.pointapp.config.security.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

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
     *
     */

    //  1. 유저 정보 변경
    @Override
    public void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException("해당하는 uuid가 없습니다. " + uuid));
        userRepository.save(
                User.builder()
                        .id(user.getId())
                        .loginId(user.getLoginId())
                        .roll(user.getRoll())
                        .address(userUpdateInfoDto.getAddress())
                        .pointPassword(user.getPointPassword())
                        .userName(user.userName())
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
    // todo: 아이디는 동일시 불가, 전화번호 중간, 끝 4자리 포함시 불가
    @Override
    public String updateUserPwd(String passWord, String newPassword, String token)  {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()-> new NoSuchElementException("해당하는 유저가 존재하지 않습니다"));;

        String phoneNum = user.getPhoneNumber();

        // 전화번호의 중간 4자리와 끝 4자리를 추출하여 비밀번호와 비교
        String middleNum = phoneNum.substring(3, 7);
        String lastNum = phoneNum.substring(phoneNum.length() - 4);
        log.info("middleNum, lastNum {}, {}", middleNum,lastNum);

        // 패스워드 변경 조건
        if (!new BCryptPasswordEncoder().matches(passWord, user.getPassword())) {
            return "사용할 수 없는 패스워드 입니다.";
        } else if (newPassword.contains(user.getLoginId())) {
            return "아이디의 일부를 포함한 패스워드는 사용할 수 없습니다";
        } else if (newPassword.contains(middleNum) || newPassword.contains(lastNum)) {
            return "전화번호의 일부를 포함한 패스워드는 사용할 수 없습니다.";
        } else {
            // 새로운 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
            user.hashPassword(newPassword);
            return "패스워드가 업데이트되었습니다.";
        }
    }

    //  3. 유저 패스워드 찾기 및 변경
    public String SearchUserPwd(String loginId,String newPassword)  {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new NoSuchElementException("해당하는 유저가 존재하지 않습니다"));;

        String phoneNum = user.getPhoneNumber();

        // 전화번호의 중간 4자리와 끝 4자리를 추출하여 비밀번호와 비교
        String middleNum = phoneNum.substring(3, 7);
        String lastNum = phoneNum.substring(phoneNum.length() - 4);
        log.info("middleNum, lastNum {}, {}", middleNum, lastNum);

        // 패스워드 변경 조건
        if (newPassword.contains(user.getLoginId())) {
            return "아이디의 일부를 포함한 패스워드는 사용할 수 없습니다";
        } else if (newPassword.contains(middleNum) || newPassword.contains(lastNum)) {
            return "전화번호의 일부를 포함한 패스워드는 사용할 수 없습니다.";
        } else {
            // 새로운 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
            user.hashPassword(newPassword);
            return "패스워드가 업데이트되었습니다.";
        }
    }


    //  4. 유저 포인트 패스워드 변경
    @Override
    public void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException ("해당하는 uuid가 없습니다. " + uuid));
        // 새로운 포인트 패스워드를 시큐리티 패스워드 인코더로 암호화하여 저장
        user.hashPointPassword(userUpdatePointPwdDto.getNewPointPassword());
    }

    //  5. 유저 탈퇴 패스워드 확인
    @Override
    public Boolean userLeavePwd(String password, String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        User user = userRepository.findByUuid(uuid).get();
        log.info("user : {}",user );
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            return false;
        } else {
            return true;
        }
    }

    //  6. 유저 탈퇴(상태변경)
    @Override
    public void userLeaveOnline(String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        Optional<User> user = userRepository.findByUuid(uuid);
        user.get().leaveOnlineStatus();
    }

    //  7. 회원가입 시 로그인 중복 확인
    @Override
    public Boolean validateLoginInd(String loginId) {
        return !userRepository.findByLoginId(loginId).isPresent();
    }

    //  8. 아이디 찾기(유저 이름, 유저 휴대폰 번호로 조회)
    @Override
    public String getUserByNameAndPhoneNumber(String userName, String phoneNumber) {
        // findByUserNameAndPhoneNumber 매개변수 순서 엔티티 순서랑 관련있음 ex) userName가 먼저 phoneNumber보다 엔티티에 선언됨
        log.info("findByUserNameAndPhoneNumberuserName : {}, {} ",userName, phoneNumber);

        log.info("findByUserNameAndPhoneNumberuserName : {} ", userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber));

        log.info("user : {}",userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber).map(User::getLoginId));

        return userRepository.findByUserNameAndPhoneNumber(userName, phoneNumber)
                .map(User::getLoginId)
                .orElse(null);
    }

//    //  8. 비밀번호 찾기(유저 아이디, 유저 이름,유저 휴대폰 번호 조회)
//    @Override
//    public String getUserByIdAndNameAndPhoneNumber(String loginId, String userName, String phoneNumber) {
//
//        return userRepository
//                .findByLoginIdAndUserNameAndPhoneNumber(userName, phoneNumber, phoneNumber)
//                .map(User::getLoginId)
//                .orElse(null);
//    }
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
