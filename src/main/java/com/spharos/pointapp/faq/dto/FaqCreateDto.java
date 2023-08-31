package com.spharos.pointapp.faq.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FaqCreateDto {
    private String title;
    private String context;
}
