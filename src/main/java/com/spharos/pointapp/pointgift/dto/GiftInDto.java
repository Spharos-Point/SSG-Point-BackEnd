package com.spharos.pointapp.pointgift.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GiftInDto {

    private Integer point;
    private String giftMessage;
    private String pointPassword;
    private String recipientLoginId;
    private String giverUuid;


}
