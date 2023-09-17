package com.spharos.pointapp.auth;
import com.spharos.pointapp.auth.vo.AuthenticationRequest;
import com.spharos.pointapp.auth.vo.AuthenticationResponse;
import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponseStatus;
import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.pointcard.domain.PointCard;
import com.spharos.pointapp.pointcard.infrastructure.PointCardRepository;
import com.spharos.pointapp.user.domain.Roll;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import com.spharos.pointapp.userpoint.pointList.infrastructure.UserPointListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import static com.spharos.pointapp.config.common.BaseResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PointCardRepository pointCardRepository;
    private final UserPointListRepository userPointListRepository;

    /**
     *
     * 1. 시큐리티 회원가입
     * 2. 바코드 생성
     * 3. 바코드 유효성 검사
     * 4. 로그인 기능
     *
     */


//    1. 시큐리티 로그인
//    @Transactional(readOnly = false)
    public AuthenticationResponse signup(UserSignUpDto userSignUpDto) throws BaseException {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        log.info("userSignUpDto is : {}", userSignUpDto);

        User user = User.builder()
                .loginId(userSignUpDto.getLoginId())
                .uuid(uuidString)
                .roll(Roll.USER)
                .userName(userSignUpDto.getName())
                .password(userSignUpDto.getPassword())
                .email(userSignUpDto.getEmail())
                .phoneNumber(userSignUpDto.getPhone())
                .address(userSignUpDto.getAddress())
                .status(1)
                .build();

        user.hashPassword(user.getPassword());
        userRepository.save(user);
//         바코드 생성 및 저장
        createAndSavePointCard(user, uuidString);

        log.info("user 2 {} ", user);
        return AuthenticationResponse.builder()
                .build();
    }

    private void createAndSavePointCard(User user, String uuidString) throws BaseException {
        String pointCardBarcode = pointCardBarcodeGenerator(user);
        String validatedBarcode = validateBarcode(pointCardBarcode);

        PointCard pointCard = PointCard.builder()
                .brandId(7)
                .cardnumber(validatedBarcode)
                .uuid(uuidString)
                .build();

        pointCardRepository.save(pointCard);
    }

    //    2. 바코드 생성 (9350 + id값(8자리) + 랜덤 4자리)
    private String pointCardBarcodeGenerator(User user) {
        String fixedPrefix = "935000000000";
        Long id = user.getId();
        return generatePointCardBarcode(fixedPrefix, id);
    }

    private String generatePointCardBarcode(String prefix, Long id) {
        int length_id = (int) (Math.log10(id) + 1);
        String prefix_barcode = prefix.substring(0, prefix.length() - length_id);
        String sum_barcode = prefix_barcode + id;
        Random random = new Random();

        String full_barcode = sum_barcode + String.format("%04d", random.nextInt(1000));
        log.info("full_barcode is : {}", full_barcode);

        return full_barcode;
    }

    //    3. 바코드 유효성 검사
    private String validateBarcode(String checkCardNumber) throws BaseException{
        Optional<PointCard> byBarCode = pointCardRepository.findByCardnumber(checkCardNumber);
        log.info("byBarCode is : {}", byBarCode);
        if (byBarCode.isPresent()) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_CARD_NUMBER);

        }
        String substring = checkCardNumber.substring(12, 15);
        int endbarcode = Integer.parseInt(substring) + 1;

        String barcode = checkCardNumber.substring(0, 12) + String.format("%04d", endbarcode);
        return barcode;

    }

    //    4. 로그인 기능
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws BaseException {
        User user = userRepository.findByLoginId(authenticationRequest.getLoginId())
                .orElseThrow(() -> new BaseException(FAILED_TO_LOGIN));
        String JwtToken = jwtTokenProvider.generateToken(user);

        if(user.getStatus() == 0) {
            throw new BaseException(BaseResponseStatus.WITHDRAWAL_USER);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        // uuid로 로그인 가능
                        user.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .name(user.getName())
                .build();

    }

}
