package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.dto.*;

import java.util.Optional;

public interface UserService {
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String uuid);
    void updateUserPwd(UserUpdatePwdDto userUpdatePwdDto, String uuid);
    void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String uuid);
    Boolean userLeavePwd(String password, String uuid);
    void userLeaveOnline(String uuid);
    Boolean validateLoginInd(String loginId);
}

