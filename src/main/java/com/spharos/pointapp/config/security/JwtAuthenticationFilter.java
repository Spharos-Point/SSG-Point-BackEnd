package com.spharos.pointapp.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull
            HttpServletRequest request,
            @NonNull
            HttpServletResponse response,
            @NonNull
            FilterChain filterChain
    ) throws ServletException, IOException {
        // authHeader = baerer token 있음
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String loginId;
        // 즉, 넘어오지 않아 토큰값이 없거나 bearer로 시작하는 값이 없는경우
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        loginId = jwtTokenProvider.getLoginId(jwt);
        log.info("jwt doFilterInternal : {}", jwt);
        log.info("loginId doFilterInternal : {}", loginId);
        log.info("SecurityContextHolder.getContext(): {}", SecurityContextHolder.getContext().getAuthentication());

        // 해당되는 uuid가 있고 시큐리티를 통해 생성된 uuid인 경우 즉, 토큰이 맞는경우
        if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginId);
            log.info("userDetails : {}", userDetails);

            // 유효값 확인 즉, 정상 토큰인지 확인하여 승인하는 작업, 복붙해서 사용하는 코드
            if(jwtTokenProvider.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // jwt 토큰을 받아와서 시큐리티 서비스 안에 생성하는 토큰 생성
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            log.info("request response : {} {}", request, response);

            filterChain.doFilter(request, response);
        }
    }
}
