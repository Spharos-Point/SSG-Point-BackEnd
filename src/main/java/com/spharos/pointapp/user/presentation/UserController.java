package com.spharos.pointapp.user.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.user.application.UserService;
import com.spharos.pointapp.user.dto.*;
import com.spharos.pointapp.user.vo.*;
import io.swagger.v3.oas.annotations.Operation;
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
     * 8. 유저 이름, 유저 휴대폰 번호로 조회
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

    // 1. 유저 정보 변경
    @Operation(summary = "유저 정보 변경", description = "이메일과 주소를 변경합니다.", tags = { "User Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PutMapping("/myinfo/changeInfo")
    public BaseResponse<String> updateUserInfo(@RequestHeader("Authorization") String token,
                                               @RequestBody UserUpdateInfoVo userUpdateInfoVo) {

        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        try {
            UserUpdateInfoDto userUpdateInfoDto = modelMapper.map(userUpdateInfoVo, UserUpdateInfoDto.class);
            userService.updateUserInfo(userUpdateInfoDto, uuid);
            return new BaseResponse<>("회원정보를 변경하였습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 2. 유저 패스워드 변경
    @Operation(summary = "유저 패스워드 변경", description = "패스워드를 변경합니다.", tags = { "User Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PutMapping("/changePwd")
    public BaseResponse<String> updateUserPwd(@RequestHeader("Authorization") String token,
                                                @RequestBody UserUpdatePwdVo userUpdatePwdVo) {

        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        try {
            userService.updateUserPwd(userUpdatePwdVo.getPassword(), userUpdatePwdVo.getNewPassword(), uuid);
            return new BaseResponse<>("패스워드를 변경하였습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 3. 유저 패스워드 찾기 및 변경
    @Operation(summary = "유저 패스워드 찾기 및 변경", description = "유저 패스워드 찾기 및 변경", tags = { "User Controller" })
    @PutMapping("/Search/Pwd")
    public BaseResponse<String> SearchUserPwd(@RequestBody UserSearchPwdInVo userSearchPwdInVo){

        try {
            userService.SearchUserPwd(userSearchPwdInVo.getLoginId(), userSearchPwdInVo.getNewPassword());
            return new BaseResponse<>("패스워드를 변경하였습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 4. 유저 포인트 패스워드 변경
    @Operation(summary = "포인트 패스워드 확인 및 변경", description = "포인트 패스워드를 확인 하고 변경한다", tags = { "User Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PutMapping("/changePointPwd")
    public BaseResponse<String> updateUserPointPwd(@RequestHeader("Authorization") String token,
                                   @RequestBody UserUpdatePointPwdVo userUpdatePointPwdVo) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        UserUpdatePointPwdDto updatePointPwDto = modelMapper.map(userUpdatePointPwdVo, UserUpdatePointPwdDto.class);


        try {
            userService.updateUserPointPwd(updatePointPwDto, uuid);
            return new BaseResponse<>("패스워드를 변경하였습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    // 5. 유저 탈퇴 패스워드 확인
    @Operation(summary = "탈퇴 패스워드 확인", description = "탈퇴 패스워드 확인", tags = { "User Controller" })
    @SecurityRequirement(name = "Bearer Auth") // 토큰이 필요한 보안 요구 사항 추가
    @PostMapping("/leavePwd")
    public BaseResponse<String> leavePwd(@RequestHeader("Authorization") String token,
                                            @RequestBody UserLeavePwdVo userLeavePwdVo) {

        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        try {
            userService.userLeavePwd(userLeavePwdVo.getPassword(), uuid);
            return new BaseResponse<>("패스워드가 일치합니다");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 6. 유저 탈퇴(상태변경)
    @Operation(summary = "유저 탈퇴", description = "상태를 변경 해준다.", tags = { "User Controller" })
    @PutMapping("/leaveOnline")
    public BaseResponse<String> leaveOnilne(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        try {
            userService.userLeaveOnline(uuid);
            return new BaseResponse<>("탈퇴가 완료 되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 7. 회원가입 시 로그인 중복 확인
    @Operation(summary = "회원가입 (회원가입 로그인 중복 확인)", description = "회원가입 시 로그인 중복 확인 해준다.", tags = { "User Controller" })
    @GetMapping("/validateLoginId/{loginId}")
    public BaseResponse<String> validateLogin(@PathVariable("loginId") String loginId) {
        try {
            userService.validateLoginInd(loginId);
            return new BaseResponse<>("사용할 수 있는 아이디입니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //  8. 아이디 찾기(유저 이름, 유저 휴대폰 번호로 조회)
    @Operation(summary = "아이디 찾기(유저 이름, 유저 휴대폰 번호로 조회)", description = "아이디 찾기", tags = { "User Controller" })
    @GetMapping("/search/NameAndPhoneNum")
    public BaseResponse<String> searchingPhoneNum(@RequestParam("userName") String userName,
                                                    @RequestParam("phoneNumber") String phoneNumber)   {

        try {
            String loginId = userService.getUserLoginIdByNameAndPhoneNumber(userName, phoneNumber);
            return new BaseResponse<>(loginId);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    //  9. 비밀번호 찾기(유저 아이디, 유저 이름,유저 휴대폰 번호 조회)
    @Operation(summary = "비밀번호 찾기(유저 아이디, 유저 이름,유저 휴대폰 번호 조회)", description = "비밀번호 찾기", tags = { "User Controller" })
    @GetMapping("/search/IdAndNameAndPhoneNum")
    public BaseResponse<String> searchingPhoneNum(@RequestParam("loginId") String loginId,
                                                  @RequestParam("userName") String userName,
                                                  @RequestParam("phoneNumber") String phoneNumber) {

        try {
            userService.getUserByIdAndNameAndPhoneNumber(loginId, userName, phoneNumber);
            return new BaseResponse<>("비밀번호 찾기를 성공했습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
