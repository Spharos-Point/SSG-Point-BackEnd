package com.spharos.pointapp.user.presentation;

import com.spharos.pointapp.user.application.UserService;
import com.spharos.pointapp.user.dto.UserGetDto;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import com.spharos.pointapp.user.dto.UserUpdateDto;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import com.spharos.pointapp.user.vo.UserGetOut;
import com.spharos.pointapp.user.vo.UserSignUpIn;
import com.spharos.pointapp.user.vo.UserUpdate;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/member")
    public void createUser(@RequestBody UserSignUpIn userSignUpIn) {
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .loginId(userSignUpIn.getLoginId())
                .password(userSignUpIn.getPassword())
                .name(userSignUpIn.getName())
                .email(userSignUpIn.getEmail())
                .phone(userSignUpIn.getPhone())
                .address(userSignUpIn.getAddress())
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



    // 유저 정보 수정
    @PutMapping("/member")
    public void updateUser(@RequestBody UserUpdate userUpdate, @RequestHeader HttpHeaders httpHeaders) throws Exception{

        log.info("{}",httpHeaders.get("authorization"));
        ModelMapper modelMapper = new ModelMapper();
        userService.updateUser(
                modelMapper.map(userUpdate, UserUpdateDto.class),
                httpHeaders.get("authorization").toString().substring(8).replace("]",""));
    }

    // point pw 변경

//    //비밀번호 확인 처리 요청
//    @PostMapping("/checkPw")
//    public String checkPw(@RequestBody String pw, HttpSession session) throws Exception {
//
//        log.info("비밀번호 확인 요청 발생");
//
//        String result = null;
//
//        UsersVO dbUser = (UsersVO)session.getAttribute("login");
//
//        if(encoder.matches(pw, User.getpassWord())) {
//            result = "pwConfirmOK";
//        } else {
//            result = "pwConfirmNO";
//        }
//
//        return result;
//
//    }



    //비밀번호 변경 요청
//    @PostMapping("/pw-change")
//    public String pwChange(@RequestBody UsersVO user, HttpSession session) throws Exception {
//
//        log.info("비밀번호 변경 요청 발생!!!");
//
//        //비밀번호 변경
//        usersService.modifyPw(user);
//
//        //비밀번호 변경 성공시 로그인 세션 객체 다시 담음
//        LoginVO modifyUser = new LoginVO();
//        modifyUser.setEmail(user.getEmail());
//
//        UsersVO mUser = usersService.login(modifyUser);
//        log.info("회원정보 불러오기 : " + mUser);
//        session.setAttribute("login", mUser);
//
//        return "changeSuccess";
//    }

}
