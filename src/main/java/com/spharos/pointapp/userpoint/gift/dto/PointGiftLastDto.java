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
    private Long giftId;
    private String senderLoginId;
    private String senderName;
    private Integer point;
    private String giftMessage;
    private LocalDateTime createdDate;
}
