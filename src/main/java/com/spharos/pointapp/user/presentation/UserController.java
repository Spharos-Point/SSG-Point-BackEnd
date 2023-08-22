package com.spharos.pointapp.user.presentation;

import com.spharos.pointapp.user.application.UserService;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import com.spharos.pointapp.user.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/member/join")
    public void createUser(@RequestBody UserSignUpInVo userSignUpInVo) {
        log.info("INPUT Object Data is : {}" , userSignUpInVo);
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .loginId(userSignUpInVo.getLoginId())
                .password(userSignUpInVo.getPassword())
                .name(userSignUpInVo.getName())
                .email(userSignUpInVo.getEmail())
                .phone(userSignUpInVo.getPhone())
                .address(userSignUpInVo.getAddress())
                .build();
        userService.createUser(userSignUpDto);
    }

    // 유저 정보 업데이트
    @PutMapping("/myinfo/changeInfo")
    public void updateUserInfo(@RequestBody UserUpdateInfoVo userUpdateInfoVo,
                               @RequestHeader HttpHeaders httpHeaders) {

        ModelMapper modelMapper = new ModelMapper();
        String uuid = httpHeaders.getFirst("uuid");

        UserUpdateInfoDto userUpdateInfoDto = modelMapper.map(userUpdateInfoVo, UserUpdateInfoDto.class);
        userService.updateUserInfo(userUpdateInfoDto, uuid);
    }

    // 유저 패스워드 변경
    @PutMapping("/changePw")
    public void updateUserPw(@RequestBody UserUpdatePwVo userUpdatePwVo,
                             @RequestHeader HttpHeaders httpHeaders) {

        ModelMapper modelMapper = new ModelMapper();
        String uuid = httpHeaders.getFirst("uuid");

        UserUpdatePwDto updatePwDto = modelMapper.map(userUpdatePwVo, UserUpdatePwDto.class);
        userService.updateUserPw(updatePwDto, uuid);
    }

    // 유저 포인트 패스워드 변경
    @PutMapping("/changePointPw")
    public void updateUserPointPw(@RequestBody UserUpdatePointPwVo userUpdatePointPwVo,
                                  @RequestHeader HttpHeaders httpHeaders) {

        ModelMapper modelMapper = new ModelMapper();
        String uuid = httpHeaders.getFirst("uuid");

        UserUpdatePointPwDto updatePointPwDto = modelMapper.map(userUpdatePointPwVo, UserUpdatePointPwDto.class);
        userService.updateUserPointPw(updatePointPwDto, uuid);
    }


    // 유저 탈퇴 패스워드 확인
    @PostMapping("/leave")
    public ResponseEntity<Boolean> leave(@RequestBody UserLeaveVo userLeaveVo,
                                             @RequestHeader HttpHeaders httpHeaders) {

        String uuid = httpHeaders.getFirst("uuid");
        User user = userRepository.findByUuid(uuid).get();

        if (!new BCryptPasswordEncoder().matches(userLeaveVo.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("패스워드가 올바르지 않습니다.");
        } else {
            return ResponseEntity.ok(true);
        }
    }

//    @GetMapping("/api/{UUID}")
//    public ResponseEntity<UserGetOut> getUserByUUID(@PathVariable String UUID) {
//        UserGetDto userGetDto = userService.getUserByUUID(UUID);
//        UserGetOut userGetOut = UserGetOut.builder()
//                .loginId(userGetDto.getLoginId())
//                .userName(userGetDto.getUserName())
//                .email(userGetDto.getEmail())
//                .phone(userGetDto.getPhone())
//                .address(userGetDto.getAddress())
//                .build();
//        log.info("OUTPUT userGetOut is : {}" , userGetOut);
//        return ResponseEntity.ok(userGetOut);
//    }

}
