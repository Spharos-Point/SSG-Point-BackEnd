package com.spharos.pointapp.affiliatecard.infrastructure;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateRepository extends JpaRepository<AffiliateCard, Long> {
}
