package com.spharos.pointapp.partner.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "partner_name")
    @Convert(converter = PartnerNameConverter.class)
    private PartnerName partnerName;
}
