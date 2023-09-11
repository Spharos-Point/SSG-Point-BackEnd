package com.spharos.pointapp.faq.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FaqUpdateDto {

    private String title;
    private String context;
}
