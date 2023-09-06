package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;

public interface UserService {
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String token);
    void updateUserPwd(UserUpdatePwdDto userUpdatePwdDto, String token);
    void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String token);
    Boolean userLeavePwd(String password, String token);
    void userLeaveOnline(String token);
    Boolean validateLoginInd(String loginId);
    String getUserByNameAndPhoneNumber(String phoneNumber, String userName);
}

