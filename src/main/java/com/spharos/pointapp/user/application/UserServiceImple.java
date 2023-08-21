package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImple implements UserService{

    private final UserRepository userRepository;

    @Override
    public void createUser(UserSignUpDto userSignUpDto) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        User user = User.builder()
                .loginId(userSignUpDto.getLoginId())
                .uuid(uuidString)
                .name(userSignUpDto.getName())
                .password(userSignUpDto.getPassword())
                .email(userSignUpDto.getEmail())
                .phone(userSignUpDto.getPhone())
                .address(userSignUpDto.getAddress())
                .status(1)
                .build();
        userRepository.save(user);
    }


    @Override
    public void updateUser(UserUpdateDto userUpdateDto, String uuid) {
        User user = userRepository.findByUuid(uuid).get();
        userRepository.save(
                User.builder()
                        .id(user.getId())
                        .loginId(user.getLoginId())
                        .address(userUpdateDto.getAddress())
                        .pointPassword(user.getPointPassword())
                        .name(user.getUsername())
                        .status(user.getStatus())
                        .uuid(user.getUuid())
                        .phone(user.getPhone())
                        .email(userUpdateDto.getEmail())
                        .password(user.getPassword())
                        .build()
        );
        log.info("{}", user);
    }
    //todo: 비밀번호 인증 실패 예외처리
    @Override
    public void updateUserPw(UserUpdatePwDto updatePwDto, String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 사용자가 입력한 현재 비밀번호와 DB에 저장된 시큐리티 패스워드를 비교
        if (!new BCryptPasswordEncoder().matches(updatePwDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        // 새로운 비밀번호와 새로운 비밀번호 확인이 일치하는지 검증
        if (!updatePwDto.getNewPassword().equals(updatePwDto.getConfirmPassword())) {
            throw new IllegalArgumentException("New passwords do not match");
        }

        // 새로운 비밀번호를 시큐리티 패스워드 인코더로 암호화하여 저장
        user.hashPassword(updatePwDto.getNewPassword());
        userRepository.save(user);
    }

    @Override
    public void updateUserPointPw(UserUpdatePointPwDto updatePointPwDto, String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.updateUserPointPw(String.valueOf(updatePointPwDto));
        userRepository.save(user);
    }


    @Override
    public UserGetDto getUserByLoginId(String loginId) {

        User user = userRepository.findByLoginId(loginId).get();
        log.info("user is : {}" , user);
        UserGetDto userGetDto = UserGetDto.builder()
                .loginId(user.getLoginId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
        return userGetDto;
    }

    @Override
    public UserGetDto getUserByUUID(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );        log.info("user is : {}" , user);
        UserGetDto userGetDto = UserGetDto.builder()
                .loginId(user.getLoginId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
        return userGetDto;
    }

}
