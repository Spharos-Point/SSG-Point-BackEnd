package com.spharos.pointapp.pointgift.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GiftCreateDto {

    private Integer point;
    private Long imageId;
    private String type;
    private String receiverUuid;
    private String giverUuid;
    private String giftMessage;

}
