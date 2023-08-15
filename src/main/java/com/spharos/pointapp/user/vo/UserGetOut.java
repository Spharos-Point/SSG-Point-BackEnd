package com.spharos.pointapp.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetOut {

    private String loginId;
    private String userName;
    private String email;
    private String phone;
    private String address;
}
