package com.spharos.pointapp.admin.administrator.dto;

import com.spharos.pointapp.admin.administrator.domain.Roll;
import com.spharos.pointapp.user.dto.UserSignUpDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class AdministratorCreateDto {
    private String roll;
    private String loginId;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;
}
