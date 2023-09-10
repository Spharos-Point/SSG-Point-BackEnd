package com.spharos.pointapp.admin.administrator.dto;

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
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
}
