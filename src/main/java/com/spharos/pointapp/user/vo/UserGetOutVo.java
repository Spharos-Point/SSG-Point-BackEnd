package com.spharos.pointapp.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetOutVo {

    private String loginId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
