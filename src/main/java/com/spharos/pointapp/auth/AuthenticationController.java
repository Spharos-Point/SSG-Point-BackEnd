package com.spharos.pointapp.auth;

import com.spharos.pointapp.auth.vo.AuthenticationRequest;
import com.spharos.pointapp.auth.vo.AuthenticationResponse;
import com.spharos.pointapp.auth.vo.SignUpRequest;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestBody UserSignUpDto userSignUpDto
    ) {
        return ResponseEntity.ok(authenticationService.signup(userSignUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        log.info("userlogin is : {}" , authenticationRequest);
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
