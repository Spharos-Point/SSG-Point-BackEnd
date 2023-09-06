package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.*;

public interface UserService {
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String token);
    void updateUserPwd(String loginId, String passWord, String NewPassword);
    void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String token);
    void userLeaveOnline(String token);
    Boolean userLeavePwd(String password, String token);
    Boolean validateLoginInd(String loginId);
    String getUserByNameAndPhoneNumber(String phoneNumber, String userName);
    String getUserByIdAndNameAndPhoneNumber(String loginId, String userName, String phoneNumber);
}

