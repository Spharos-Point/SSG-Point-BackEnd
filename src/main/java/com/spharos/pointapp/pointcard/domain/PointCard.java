package com.spharos.pointapp.pointcard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PointCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, name = "barcode")
    private String barcode;
    @Column(nullable = false, length = 100, name = "uuid")
    private String uuid;
}
