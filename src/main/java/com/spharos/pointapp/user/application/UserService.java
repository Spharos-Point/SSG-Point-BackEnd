package com.spharos.pointapp.user.application;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.user.dto.*;

public interface UserService {
    void updateUserInfo(UserUpdateInfoDto userUpdateInfoDto, String uuid) throws BaseException;
    void updateUserPwd(String passWord, String newPassword, String uuid) throws BaseException;
    void SearchUserPwd(String loginId, String newPassword) throws BaseException;
    void updateUserPointPwd(UserUpdatePointPwdDto userUpdatePointPwdDto, String uuid) throws BaseException;
    void userLeaveOnline(String uuid) throws BaseException;
    void userLeavePwd(String password, String uuid) throws BaseException;
    void validateLoginInd(String loginId) throws BaseException;
    String getUserLoginIdByNameAndPhoneNumber(String userName, String phoneNumber) throws BaseException;
    void getUserByIdAndNameAndPhoneNumber(String loginId, String userName, String phoneNumber) throws BaseException;
}

