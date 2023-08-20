package com.spharos.pointapp.user.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {

    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;

}
