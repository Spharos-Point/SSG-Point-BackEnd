package com.spharos.pointapp.coupon.domain;

import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.Store;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_place")
    private Store store;

    @Column(nullable = false, length = 100, name = "coupon_num")
    private String couponNum;

    @Column(nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;

    @Column(nullable = false, name = "coupon_value")
    private Integer couponValue; // tinyint

}