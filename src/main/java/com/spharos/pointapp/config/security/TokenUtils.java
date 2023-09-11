package com.spharos.pointapp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TokenUtils(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String extractUuidFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return jwtTokenProvider.getUuid(token.substring(7));
        }
        return null;
    }
}
