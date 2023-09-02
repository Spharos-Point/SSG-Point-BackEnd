package com.spharos.pointapp.pointcard.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PointCardCreateDto {

    private String barcode;
    private Integer cvc;
    private String partnerName;
    private String registeredStore;
    private String uuid;

}
