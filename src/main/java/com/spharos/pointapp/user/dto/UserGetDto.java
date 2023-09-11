package com.spharos.pointapp.user.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {

    private String loginId;
    private String userName;
    private String email;
    private String phone;
    private String address;

}
