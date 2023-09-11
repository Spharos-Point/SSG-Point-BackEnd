package com.spharos.pointapp.affiliatecard.domain;

import com.spharos.pointapp.extra.domain.Extra;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(nullable = false, name = "extra_id")
    private Extra extra;

    @Column(nullable = false, name = "affiliate_num")
    private String affiliateNum;

    @Column(nullable = false, name = "uuid")
    private String uuid;

}
