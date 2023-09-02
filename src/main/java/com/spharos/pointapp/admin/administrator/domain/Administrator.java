package com.spharos.pointapp.admin.administrator.domain;

import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Roll roll;
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
}
