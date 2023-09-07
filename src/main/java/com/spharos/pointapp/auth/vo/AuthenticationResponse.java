package com.spharos.pointapp.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String userName;

}
