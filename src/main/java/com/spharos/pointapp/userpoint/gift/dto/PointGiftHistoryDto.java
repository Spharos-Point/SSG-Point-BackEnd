package com.spharos.pointapp.userpoint.gift.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointGiftHistoryDto {

    private Long pointGiftId;
    private String giverLoginId;
    private String giverName;
    private Integer point;
    private String giftImage;
    private String giftMessage;
    private String createdDate;

}
