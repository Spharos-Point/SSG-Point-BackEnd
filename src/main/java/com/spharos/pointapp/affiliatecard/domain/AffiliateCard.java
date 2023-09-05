package com.spharos.pointapp.affiliatecard.domain;

import jakarta.persistence.*;
import lombok.*;
import com.spharos.pointapp.affiliatecard.domain.AffiliateType;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "affiliate_type")
    @Enumerated(EnumType.STRING)
    private AffiliateType affiliateType;

    @Column(nullable = false)
    private Long pointId;

    @Column(nullable = false, name = "affilicate_num")
    private String affilicateNum;
}
