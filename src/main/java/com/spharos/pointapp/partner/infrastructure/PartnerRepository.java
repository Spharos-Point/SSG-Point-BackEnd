package com.spharos.pointapp.partner.infrastructure;

import com.spharos.pointapp.partner.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
