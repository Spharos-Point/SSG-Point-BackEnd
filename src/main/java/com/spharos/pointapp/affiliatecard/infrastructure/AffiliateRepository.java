package com.spharos.pointapp.affiliatecard.infrastructure;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AffiliateRepository extends JpaRepository<AffiliateCard, Long> {
        AffiliateCard findTopByUuidAndExtraId(String uuid, Long extraId);
        List<AffiliateCard> findAllByUuid(String uuid);
        Optional<AffiliateCard> findByAffiliateNum(String affiliateNum);

}
