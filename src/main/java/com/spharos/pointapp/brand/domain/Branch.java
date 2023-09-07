package com.spharos.pointapp.brand.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Branch extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String address;
    private String phone;
    private String latitude;
    private String longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

}
