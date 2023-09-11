package com.spharos.pointapp.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SignUpRequest {

    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
}
