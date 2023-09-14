package com.spharos.pointapp.customer.domain;

import com.spharos.pointapp.category.domain.Category;
import com.spharos.pointapp.config.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private Long adminId;
    private String title;
    private String context;
    private String phone;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
