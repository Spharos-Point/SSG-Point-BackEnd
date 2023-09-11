package com.spharos.pointapp.faq.vo;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FaqCreate {
    private String title;
    private String context;
}
