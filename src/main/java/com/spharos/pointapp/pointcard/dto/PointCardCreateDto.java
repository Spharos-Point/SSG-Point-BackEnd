package com.spharos.pointapp.pointcard.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PointCardCreateDto {

    private String cardNumber;
    private String cvc;
    private Integer brandId;
    private Long branchId;
    private String uuid;

}
