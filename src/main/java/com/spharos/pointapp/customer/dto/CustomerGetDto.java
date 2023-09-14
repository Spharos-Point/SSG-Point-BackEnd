package com.spharos.pointapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class CustomerGetDto {
    private Long customerId;
    private Long adminId;
    private Integer categoryId;
    private String title;
    private String context;
    private String phone;
    private String answer;
}
