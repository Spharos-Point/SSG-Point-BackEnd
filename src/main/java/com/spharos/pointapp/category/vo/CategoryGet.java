package com.spharos.pointapp.category.vo;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGet {
    private Integer categoryId;
    private String categoryName;
}
