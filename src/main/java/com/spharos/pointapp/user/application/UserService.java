package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.*;

public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String uuid);
    void updateUserPwd(UserUpdatePwdDto userUpdatePwdDto, String uuid);
    void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String uuid);
    Boolean userLeavePwd(String password, String uuid);
    void userLeaveOnline(String uuid);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String uuid);

}

