package com.spharos.pointapp.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, name = "uuid")
    private String uuid; // todo: UUID
    @Column(nullable = false, length = 30, name = "login_id")
    private String loginId;
    @Column(nullable = false, length = 100, name = "user_name")
    private String name;
    @Column(nullable = false, length = 100, name = "email")
    private String email;
    @Column(nullable = false, length = 100, name = "password")
    private String password; // todo: Hashing
    @Column(length = 30, name = "phone")
    private String phone;
    @Column(length = 100, name = "address")
    private String address;
    @Column(nullable = false, length = 1, name = "status", columnDefinition = "int default 1")
    private Integer status; // todo: default 1
    @Column(length = 100, name = "point_password")
    private String pointPassword; // todo: Hashing

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10, name = "roll")
    private Roll roll;

    //todo: 수정일자, 생성일자
    public void hashPassword(String password) {
    //      this.password = password;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public void hashPointPassword(String pointPassword) {
        this.pointPassword = new BCryptPasswordEncoder().encode(pointPassword);
    }

    public void leaveOnlineStatus() {
        this.status = 0;
    }

    // 정해진 코드 이 계정이 가지고 있는 권한을 제공
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roll.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { return uuid; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 포인트 패스워드 변경
    public void updateUserPointPw(String pointPassword) {
        this.pointPassword = pointPassword;
    }

}
