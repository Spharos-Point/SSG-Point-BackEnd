package com.spharos.pointapp.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 100, name = "UUID")
    private String UUID; // todo: UUID
    @Column(nullable = false, length = 30, name = "login_id")
    private String loginId;
    @Column(nullable = false, length = 100, name = "user_name")
    private String userName;
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

    public void hashPassword(String password) {
        this.password = password;
//        this.password = new BCryptPasswordEncoder().encode(password); // todo: Hashing - spring security
    }
}
