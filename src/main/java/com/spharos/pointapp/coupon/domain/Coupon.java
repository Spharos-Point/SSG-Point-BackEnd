package com.spharos.pointapp.coupon.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, name = "coupon_name")
    private String couponName;

    @Column(nullable = false, length = 300, name = "coupon_desc")
    private String couponDesc;

    @Column(nullable = false, length = 45, name = "use_place")
    private String usePlace;

    @Column(nullable = false, length = 100, name = "coupon_num")
    private String couponNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10, name = "coupon_type")
    private CouponType couponType;

    @Column(nullable = false, name = "coupon_value")
    private Integer couponValue; // tinyint

}