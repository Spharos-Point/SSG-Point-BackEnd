package com.spharos.pointapp.pointcard.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PointCardOutDto {

    private String barcode;
    private String partnerName;
    private String createAt;

}
