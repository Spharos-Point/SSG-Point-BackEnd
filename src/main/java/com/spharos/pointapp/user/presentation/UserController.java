package com.spharos.pointapp.user.presentation;

import com.spharos.pointapp.user.application.UserService;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    /**
     *
     * 1. 유저 정보 업데이트
     * 2. 유저 패스워드 변경
     * 3. 유저 포인트 패스워드 변경
     * 4. 유저 탈퇴 패스워드 확인
     * 5. 유저 탈퇴 상태 변경
     * 6. 유저 로그인 id 조회
     *
     */

    @Operation(summary = "회원 추가 요청", description = "회원을 등록합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserGetOutVo.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    // 1. 유저 정보 업데이트
    @PutMapping("/myinfo/changeInfo")
    public void updateUserInfo(@RequestBody UserUpdateInfoVo userUpdateInfoVo,
                               @RequestHeader("uuid") String uuid) {

        UserUpdateInfoDto userUpdateInfoDto = modelMapper.map(userUpdateInfoVo, UserUpdateInfoDto.class);
        userService.updateUserInfo(userUpdateInfoDto, uuid);
    }

    // 2. 유저 패스워드 변경
    @PutMapping("/changePwd")
    public void updateUserPwd(@RequestBody UserUpdatePwdVo userUpdatePwdVo,
                              @RequestHeader("uuid") String uuid) {

        UserUpdatePwdDto updatePwDto = modelMapper.map(userUpdatePwdVo, UserUpdatePwdDto.class);
        userService.updateUserPwd(updatePwDto, uuid);
    }

    // 3. 유저 포인트 패스워드 변경
    @PutMapping("/changePointPwd")
    public void updateUserPointPwd(@RequestBody UserUpdatePointPwdVo userUpdatePointPwdVo,
                                   @RequestHeader("uuid") String uuid) {

        UserUpdatePointPwdDto updatePointPwDto = modelMapper.map(userUpdatePointPwdVo, UserUpdatePointPwdDto.class);
        userService.updateUserPointPwd(updatePointPwDto, uuid);
    }


    // 4. 유저 탈퇴 패스워드 확인
    @PostMapping("/leavePwd")
    public ResponseEntity<Boolean> leavePwd(@RequestBody UserLeavePwdVo userLeavePwdVo,
                                            @RequestHeader("uuid") String uuid) {

        if (!userService.userLeavePwd(userLeavePwdVo.getPassword(), uuid)) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    // 5. 유저 탈퇴 상태 변경
    @PutMapping("/leaveOnline")
    public void leaveOnilne(@RequestHeader("uuid") String uuid) {
        userService.userLeaveOnline(uuid);
    }


//    // 6. 회원가입 시 로그인 중복 확인
//    @GetMapping("/validateLoginId")
//    public ResponseEntity<String> validateLoginInd(@RequestParam("loginId") String loginId){
//        userService.validateLoginInd(loginId);
//        return ResponseEntity.ok("사용 가능한 아이디");
//
//    }

    /**
//     * 회원가입시 아이디 중복 확인
//     */
//
//    public void validateDuplicateLoginId(UserSignUpDto userSignUpDto) {
//        log.info("userSignUpDto={}", userSignUpDto.getLoginId());
//        Optional<User> byLoginId = userRepository.findByLoginId(userSignUpDto.getLoginId());
//        if(byLoginId.isPresent()){
//            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
//        }
//    }




//     강사님 코드 uuid로 조회
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
