package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.*;

public interface UserService {
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String token);
    String updateUserPwd(String passWord, String newPassword, String token);
    String SearchUserPwd(String loginId, String newPassword);
    void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String token);
    void userLeaveOnline(String token);
    Boolean userLeavePwd(String password, String token);
    Boolean validateLoginInd(String loginId);
    String getUserByNameAndPhoneNumber(String userName, String phoneNumber);
//    String getUserByIdAndNameAndPhoneNumber(String loginId, String userName, String phoneNumber);
}

