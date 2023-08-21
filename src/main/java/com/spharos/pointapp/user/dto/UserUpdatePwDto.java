package com.spharos.pointapp.user.dto;

import lombok.*;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePwDto {
    private String password;
    private String newPassword;
    private String confirmPassword;

}
