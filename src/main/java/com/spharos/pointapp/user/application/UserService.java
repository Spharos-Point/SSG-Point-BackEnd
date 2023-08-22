package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.*;

public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String uuid);
    void updateUserPw(UserUpdatePwDto userUpdatePwDto, String uuid);
    void updateUserPointPw(UserUpdatePointPwDto userUpdatePointPwDto, String uuid);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String uuid);

}

