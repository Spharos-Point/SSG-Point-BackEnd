package com.spharos.pointapp.user.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdatePwVo {
    private String password;
    private String newPassword;
    private String confirmPassword;
}
