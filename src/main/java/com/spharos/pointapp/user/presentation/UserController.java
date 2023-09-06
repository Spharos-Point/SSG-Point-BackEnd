package com.spharos.pointapp.user.presentation;

import com.spharos.pointapp.user.application.UserService;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
     *  todo: get --> post로 변경
     * 1. 유저 정보 변경
     * 2. 윺저 패스워드 변경
     * 3. 유저 포인트 패스워드 변경
     * 4. 유저 탈퇴 패스워드 확인
     * 5. 유저 탈퇴 (상태변경)
     * 6. 회원가입 시 로그인 중복 확인
     * 7. 유저 휴대폰 번호, 유저 네임 으로 조회
     *
     */

//    @Operation(summary = "회원 추가 요청", description = "회원을 등록합니다.", tags = { "User Controller" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(schema = @Schema(implementation = UserGetOutVo.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
//    String loginId = jwtTokenProvider.getUuid(authHeader.substring(7));
    //  1. 유저 정보 업데이트
    @Operation(summary = "유저 정보 변경", description = "이메일과 주소를 변경합니다.", tags = { "User Controller" })
    @PutMapping("/myinfo/changeInfo")
    public void updateUserInfo(@RequestHeader("Authorization") String token,
                               @RequestBody UserUpdateInfoVo userUpdateInfoVo) {

        UserUpdateInfoDto userUpdateInfoDto = modelMapper.map(userUpdateInfoVo, UserUpdateInfoDto.class);
        userService.updateUserInfo(userUpdateInfoDto, token);

    }

    //  2. 유저 패스워드 변경
    // todo: 토큰이 아니라 로그인아이디 이름 전화번호
    @Operation(summary = "유저 정보 변경", description = "이메일과 주소를 변경합니다.", tags = { "User Controller" })
    @PutMapping("/changePwd")
    public void updateUserPwd(@RequestHeader("Authorization") String token,
                              @RequestBody UserUpdatePwdVo userUpdatePwdVo){

        UserUpdatePwdDto updatePwDto = modelMapper.map(userUpdatePwdVo, UserUpdatePwdDto.class);
        userService.updateUserPwd(updatePwDto, token);
    }

    //  3. 유저 포인트 패스워드 변경
    @PutMapping("/changePointPwd")
    @SecurityRequirement(name = "Bearer Authentication")
    public void updateUserPointPwd(@RequestHeader("Authorization") String token,
                                   @RequestBody UserUpdatePointPwdVo userUpdatePointPwdVo) {

        UserUpdatePointPwdDto updatePointPwDto = modelMapper.map(userUpdatePointPwdVo, UserUpdatePointPwdDto.class);
        userService.updateUserPointPwd(updatePointPwDto, token);
    }


    //  4. 유저 탈퇴 패스워드 확인
    @PostMapping("/leavePwd")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> leavePwd(@RequestHeader("Authorization") String token,
                                            @RequestBody UserLeavePwdVo userLeavePwdVo) {

        if (userService.userLeavePwd(userLeavePwdVo.getPassword(), token)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력하신 정보로 가입된 신세계포인트 회원이 없습니다.");
        }
    }

    //  5. 유저 탈퇴(상태변경)
    @PutMapping("/leaveOnline")
    public void leaveOnilne(@RequestHeader("Authorization") String token) {
        userService.userLeaveOnline(token);
    }

    //  6. 회원가입 시 로그인 중복 확인
    @PostMapping("/validateLoginId")
    public ResponseEntity<String> validateLogin(@RequestBody UserValidateLoginIdInVo userValidateLoginIdInVo){
        boolean isLoginValid = userService.validateLoginInd(userValidateLoginIdInVo.getLoginId());
        log.info("isLoginValid {} ",isLoginValid);
        if (isLoginValid) {
            return ResponseEntity.ok("아이디 사용 가능합니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        }
    }

    //  7. 유저 휴대폰 번호, 유저 네임 으로 조회
    @PostMapping("/search/NameAndPhoneNum")
    public ResponseEntity<String> searchingPhoneNum(@RequestBody UserSearchNameAndNumInVo userSearchNameAndNumInVo) {
        String loginId = userService.getUserByNameAndPhoneNumber(userSearchNameAndNumInVo.getPhoneNumber(), userSearchNameAndNumInVo.getUserName());
        log.info("loginId {} ",loginId);
        if (loginId != null) {
            return ResponseEntity.ok(loginId);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력하신 정보로 가입된 신세계포인트 회원이 없습니다.");
        }
    }

//    //  8. 유저 휴대폰 번호, 유저 네임 으로 조회
//    @PostMapping("/search/NameAndPhoneNum")
//    public ResponseEntity<String> searchingPhoneNum(@RequestParam("userName") String userName,
//                                                    @RequestParam("phoneNumber") String phoneNumber) {
//        String loginId = userService.getUserByNameAndPhoneNumber(userName, phoneNumber);
//
//        log.info("loginId {} ",loginId);
//        if (loginId != null) {
//            return ResponseEntity.ok(loginId);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력하신 정보로 가입된 신세계포인트 회원이 없습니다.");
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
