package com.spharos.pointapp.admin.administrator.infrastructure;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.admin.administrator.domain.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    List<Administrator> findByRoll(Roll roll);
    Administrator findByLoginId(String loginId);
}
