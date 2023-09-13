package com.spharos.pointapp.coupon.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import com.spharos.pointapp.partner.domain.Partner;

//  생성날짜, 수정날짜 필요하므로 base entity

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 45, name = "coupon_name")
    private String couponName;

    @Column(nullable = false, length = 300, name = "coupon_desc")
    private String couponDesc;

    @Column(nullable = false, name = "reg_date")
    private String regDate;

    @Column(nullable = false, name = "end_date")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @Column(nullable = false, length = 100, name = "coupon_num")
    private String couponNum;

    @Column(nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;

    @Column(nullable = false, name = "coupon_value")
    private Integer couponValue; // tinyint

    @Column(nullable = false, name = "coupon_logo_img")
    private String couponLogoImg;

    @Column(nullable = false, name = "coupon_img")
    private String couponImg;

    @Column(nullable = false, name = "coupon_value_img")
    private String couponValueImg;

}