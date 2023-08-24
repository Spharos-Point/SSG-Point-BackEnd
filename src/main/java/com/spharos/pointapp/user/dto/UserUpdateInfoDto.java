package com.spharos.pointapp.user.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateInfoDto {
    private String address;
    private String email;
}
