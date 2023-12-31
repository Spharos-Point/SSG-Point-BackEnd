package com.spharos.pointapp.user.infrastructure;

import com.spharos.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUuid(String uuid);
    Optional<User> findByUserNameAndPhoneNumber(String userName, String phoneNumber);
    boolean existsByLoginId(String loginId);
    boolean existsByLoginIdAndUserNameAndPhoneNumber(String loginId, String userName, String phoneNumber);

}
