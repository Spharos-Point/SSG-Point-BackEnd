package com.spharos.pointapp.customer.domain;

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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private Long adminId;
    private String title;
    private String context;
    private String phone;
    private String answer;
    private String writeDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerCategory customerCategory;

}
