package com.spharos.pointapp.user.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdatePwdVo {

    private String loginId;
    private String password;
    private String newPassword;
}
