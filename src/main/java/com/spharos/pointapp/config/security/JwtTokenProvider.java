package com.spharos.pointapp.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Environment env;


    public String getUuid(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //extractAllClaims를 통해 토큰의 정보를 풀고 빌드를 위한 객체
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {

        return generateToken(Map.of(), userDetails);
    }
    //토큰 생성
    public String generateToken(
            Map<String, Objects> extractClaims,
            UserDetails userDetails
    ) {
        log.info("generateToken {}, {} ", extractClaims, userDetails);
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + env.getProperty("JWT.EXPIRATION_TIME", Long.class)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //토큰 유효성 검사
    public boolean validateToken(String token, UserDetails userDetails) {
        final String uuid = getUuid(token);
        // 뽑아온 UUID와 받은 UUID가 같고 유효기간이 지나지 않았다면
        return (uuid.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //extractClaim에서 토큰이 넘어와서 토큰을 풀어주는 메소드
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//3개로 이루어진 키값을 풀기위한 메소드
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("JWT.SECRET_KEY"));
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
