package com.spharos.pointapp.pointcard.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PointCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, name = "barcode")
    private String barcode;
    @Column(nullable = false, length = 20, name = "issuer")
    private String issuer;
    @Column(nullable = false, length = 100, name = "uuid")
    private String uuid;

}
