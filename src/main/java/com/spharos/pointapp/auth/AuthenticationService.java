package com.spharos.pointapp.auth;
import com.spharos.pointapp.auth.vo.AuthenticationRequest;
import com.spharos.pointapp.auth.vo.AuthenticationResponse;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.user.domain.Roll;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationResponse signup(UserSignUpDto userSignUpDto) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        log.info("userSignUpDto is : {}" , userSignUpDto);

        User user = User.builder()
                .loginId(userSignUpDto.getLoginId())
                .uuid(uuidString)
                .roll(Roll.USER)
                .name(userSignUpDto.getName())
                .password(userSignUpDto.getPassword())
                .email(userSignUpDto.getEmail())
                .phone(userSignUpDto.getPhone())
                .address(userSignUpDto.getAddress())
                .status(1)
                .build();
        user.hashPassword(user.getPassword());
        userRepository.save(user);

        return AuthenticationResponse.builder()
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        log.info("userlogin is : {}" , authenticationRequest);

        User user = userRepository.findByLoginId(authenticationRequest.getLoginId()).orElseThrow(() -> new RuntimeException("User not found"));
        // 입력한 비밀번호를 미리 암호화

        if (new BCryptPasswordEncoder().matches(authenticationRequest.getPassword(), user.getPassword())) {
            // 패스워드 일치하여 로그인 성공
            String JwtToken = jwtTokenProvider.generateToken(user);
            log.info("JwtToken is : {}" , JwtToken);
            return  AuthenticationResponse.builder()
                    .token(JwtToken)
                    .build();
        } else {
            // 패스워드 불일치로 인한 로그인 실패
            throw new BadCredentialsException("로그인 정보가 일치하지 않습니다.");
        }
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
//        log.info("userlogin is : {}" , authenticationRequest);
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getLoginId(),
//                        authenticationRequest.getPassword()
//                )/
//        );
//
//        User user = userRepository.findByLoginId(authenticationRequest.getLoginId()).orElseThrow();
//        String JwtToken = jwtTokenProvider.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(JwtToken)
//                .build();
//    }
}
