package com.spharos.pointapp.user.presentation;


import com.spharos.pointapp.user.application.UserService;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static ModelMapper modelMapper;

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

    // 유저 정보 업데이트
    @PutMapping("/myinfo/changeInfo")
    public void updateUser(@RequestBody UserUpdateInfoVo userUpdate, @RequestHeader HttpHeaders httpHeaders) throws Exception{

        log.info("{}",httpHeaders.get("authorization"));
        userService.updateUser(
                modelMapper.map(userUpdate, UserUpdateDto.class),
                httpHeaders.get("authorization").toString().substring(8).replace("]",""));
    }

    @PutMapping("/api/v1/chagngPw")
    public void updateUserPw(@RequestBody UserUpdatePwVo userUpdatePwVo,
                             @RequestHeader HttpHeaders httpHeaders) {

        String uuid = httpHeaders.getFirst("uuid");

        log.info("uuid {}",uuid);
        log.info("chage pw");
        // 사용자 UUID를 사용하여 비밀번호 변경 처리
        UserUpdatePwDto updatePwDto = modelMapper.map(userUpdatePwVo, UserUpdatePwDto.class);
        userService.updateUserPw(updatePwDto, uuid);
    }

//    //   유저 비밀번호 수정
//    @PutMapping("/member")
//    public void updateUserPw(@RequestBody UserUpdatePwVo userUpdatePwVo,
//                             @RequestHeader("Authorization") String authorizationHeader,
//                             @RequestHeader("uuid") String userUuid) {
//        // JWT 토큰 추출
//        String jwtToken = authorizationHeader.replace("Bearer ", "");
//        ModelMapper modelMapper = new ModelMapper();
//        // 토큰을 사용하여 비밀번호 변경 처리
//        UserUpdatePwDto updatePwDto = modelMapper.map(userUpdatePwVo, UserUpdatePwDto.class);
//        userService.updateUserPw(updatePwDto, userUuid);
//    }

    // 포인트 비번 수정
//    @PutMapping("/myinfo/changePointPw")
//    public void modifyUserPointPw(@RequestBody UserUpdatePointPwVo userUpdatePointPwInVo) {
//        ModelMapper modelMapper = new ModelMapper();
//        UserUpdatePointPwDto updatePointPwDto = modelMapper.map(userUpdatePointPwInVo, UserUpdatePointPwDto.class);
//        userService.updateUserPointPw(updatePointPwDto,userUpdatePointPwInVo.getUuid());
//    }

}
