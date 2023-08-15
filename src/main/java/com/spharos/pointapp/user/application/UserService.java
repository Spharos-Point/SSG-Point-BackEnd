package com.spharos.pointapp.user.application;

import com.spharos.pointapp.user.dto.UserGetDto;
import com.spharos.pointapp.user.dto.UserSignUpDto;

import java.util.List;
public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String UUID);
    List<UserGetDto> getAllUsers();
}

