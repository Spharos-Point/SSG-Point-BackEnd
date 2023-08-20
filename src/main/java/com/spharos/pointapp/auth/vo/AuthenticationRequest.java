package com.spharos.pointapp.auth.vo;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String loginId;
    private String password;
}
