package com.spharos.pointapp.userpoint.gift.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointGiftLastDto {
    private Long pointGiftId;
    private String giverLoginId;
    private String giverName;
    private Integer point;
    private String giftImage;
    private String giftMessage;
    private String createdDate;
}
