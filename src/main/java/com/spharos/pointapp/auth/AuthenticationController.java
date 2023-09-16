package com.spharos.pointapp.auth;

import com.spharos.pointapp.auth.vo.AuthenticationRequest;
import com.spharos.pointapp.auth.vo.AuthenticationResponse;
import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "회원가입", description = "회원가입", tags = { "User Sign" })
    @PostMapping("/signup")
    public BaseResponse<AuthenticationResponse> signup (@RequestBody UserSignUpDto userSignUpDto) {
        try {
            AuthenticationResponse authenticationResponse = authenticationService.signup(userSignUpDto);
            return new BaseResponse<>(authenticationResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    @Operation(summary = "로그인", description = "로그인", tags = { "User Sign" })
    @PostMapping("/login")
    public BaseResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
            return new BaseResponse<>(authenticationResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

}
