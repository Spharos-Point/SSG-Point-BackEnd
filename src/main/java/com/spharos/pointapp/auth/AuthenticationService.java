package com.spharos.pointapp.auth;
import com.spharos.pointapp.auth.vo.AuthenticationRequest;
import com.spharos.pointapp.auth.vo.AuthenticationResponse;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.pointcard.domain.PointCard;
import com.spharos.pointapp.pointcard.infrastructure.PointCardRepository;
import com.spharos.pointapp.user.domain.Roll;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PointCardRepository pointCardRepository;

    /**
     * 1. 시큐리티 아이디 생성
     * 2. 바코드 생성
     * 3. 바코드 유효성 검사
     * 4. 로그인 기능
     */

//    1. 시큐리티 로그인
    @Transactional(readOnly = false)
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

        // 바코드 생성 및 저장
        createAndSavePointCard(user, uuidString);

        return AuthenticationResponse.builder()
                .build();
    }

    private PointCard createAndSavePointCard(User user, String uuidString) {
        String pointCardBarcode = pointCardBarcodeGenerator(user);
        String validatedBarcode = validateBarcode(pointCardBarcode);

        PointCard pointCard = PointCard.builder()
                .barcode(validatedBarcode)
                .issuer("신세계포인트닷컴")
                .uuid(uuidString)
                .build();

        return pointCardRepository.save(pointCard);
    }

//    2. 바코드 생성 (9350 + id값(8자리) + 랜덤 4자리)
    private String pointCardBarcodeGenerator(User user) {
        String fixedPrefix = "935000000000";
        Long id = user.getId();
        return generatePointCardBarcode(fixedPrefix, id);
    }

    private String generatePointCardBarcode(String prefix, Long id) {
        int length_id = (int)(Math.log10(id)+1);
        String prefix_barcode = prefix.substring(0, prefix.length()-length_id);
        String sum_barcode = prefix_barcode+id;
        Random random = new Random();

        String full_barcode = sum_barcode +String.format("%04d", random.nextInt(1000));
        log.info("full_barcode is : {}" , full_barcode);

        return full_barcode;
    }
//    3. 바코드 유효성 검사
    private String validateBarcode(String checkBarcode) {
        Optional<PointCard> byBarCode = pointCardRepository.findByBarcode(checkBarcode);
        log.info("byBarCode is : {}" , byBarCode);

        // DB에 없다면 무한 반복
        if (byBarCode.isPresent()) {
            String substring = checkBarcode.substring(12, 15);
            int endbarcode = Integer.parseInt(substring) + 1;

            String barcode = checkBarcode.substring(0, 12) + String.format("%04d", endbarcode);
            return validateBarcode(barcode);
        } else {
            log.info("checkBarcode is : {}" , checkBarcode);

            return checkBarcode;
        }
    }
//    4. 로그인 기능
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws AuthenticationException{
        log.info("userlogin is : {}" , authenticationRequest);

            User user = userRepository.findByLoginId(authenticationRequest.getLoginId()).orElseThrow();
            log.info("{}", user);
            String JwtToken = jwtTokenProvider.generateToken(user);
            log.info("{}", JwtToken);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getLoginId(),
                            authenticationRequest.getPassword()
                    )
            );
            return AuthenticationResponse.builder()
                    .token(JwtToken)
                    .build();
        } catch (AuthenticationException ex) {
            log.error("로그인 정보가 일치하지 않습니다.");
            throw new BadCredentialsException("Authentication failed", ex);
        }

    }
}
