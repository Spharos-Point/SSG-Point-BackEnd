package com.spharos.pointapp.category.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGetDto {

    private Integer categoryId;
    private String categoryName;
    private Integer parentId;

}