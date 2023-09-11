package com.spharos.pointapp.pointcard.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PointCardOutDto {

    private String cardNumber;
    private Integer brandId;
    private String createAt;
    private Long branchId;

}
