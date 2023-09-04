package com.spharos.pointapp.pointpartner.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PointPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partner_id;
    @Column(nullable = false, length = 100, name = "partner_category")
    private String partnercategory;
    @Column(nullable = false, length = 50, name = "partner_name")
    private String partnername;
    @Column(nullable = false, length = 100, name = "partner_location")
    private String partnerlocation;
    @Column(nullable = false, length = 50, name = "partner_call")
    private String partnercall;



}
