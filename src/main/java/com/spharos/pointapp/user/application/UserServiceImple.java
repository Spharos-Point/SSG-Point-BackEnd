package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.UserGetDto;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import com.spharos.pointapp.user.dto.UserUpdateDto;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        log.info("{}", uuid);

        User user = userRepository.findByUuid(uuid).get();
        log.info("{}", user);
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

    @Override
    public List<UserGetDto> getAllUsers() { return null; }
}
