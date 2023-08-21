package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.*;

import java.util.List;
public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    void updateUser(UserUpdateDto userUpdateDto, String uuid);
    void updateUserPw(UserUpdatePwDto userUpdatePwDto, String uuid);

    void updateUserPointPw(UserUpdatePointPwDto userUpdatePointPwDto, String uuid);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String uuid);

}

