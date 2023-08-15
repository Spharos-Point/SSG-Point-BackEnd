package com.spharos.pointapp.user.infrastructure;

import com.spharos.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginId(String loginId);
    User findByUUID(String UUID);
}
