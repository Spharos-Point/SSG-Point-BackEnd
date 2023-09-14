package com.spharos.pointapp.pointcard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardOutDto {

    private String cardNumber;
    private String brandName;
    private String createAt;
}
