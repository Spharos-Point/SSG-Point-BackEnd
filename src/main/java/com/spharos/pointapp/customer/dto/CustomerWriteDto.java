package com.spharos.pointapp.customer.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWriteDto {
    private Integer categoryId;
    private String title;
    private String context;
}

