package com.spharos.pointapp.extra.infrastructure;

import com.spharos.pointapp.extra.domain.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraRepository extends JpaRepository<Extra, Long> {
}
