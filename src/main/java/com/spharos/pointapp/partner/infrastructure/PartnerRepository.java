package com.spharos.pointapp.partner.infrastructure;

import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.domain.PartnerName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Long findByPartnerName(Long partnerId);

}
