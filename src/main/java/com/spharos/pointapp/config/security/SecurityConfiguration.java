package com.spharos.pointapp.config.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

// 필터중에 어떤 주소로 들어오는 값을 허가할건지, requet를 post, get 등 여러 환경 설정
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtTokenProvider;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                // 허용 범위
                                .requestMatchers("/api/v1/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider) //등록할때 사용하는 키는 authenticationProvider를 사용
                .addFilterBefore(jwtTokenProvider, UsernamePasswordAuthenticationFilter.class); //내가 만든 필터 추가

// 기존에 쓰던 형식
//        http
//           .csrf().disable()
//           .authorizeRequests()
//           .antMatchers("/api/v1/auth/**").permitAll()
//           .anyRequest().authenticated()
//           .and()
//           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//           .and()
//           .authenticationProvider(authenticationProvider)
//           .addFilterBefore((Filter) jwtTokenProvider, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
