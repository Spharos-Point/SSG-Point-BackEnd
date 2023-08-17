package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.UserGetDto;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import com.spharos.pointapp.user.dto.UserUpdateDto;
import com.spharos.pointapp.user.dto.UserUpdateDto;

import java.util.List;
public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    void updateUser(UserUpdateDto userUpdateDto, String uuid) throws Exception;

    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String uuid);
    List<UserGetDto> getAllUsers();

}

