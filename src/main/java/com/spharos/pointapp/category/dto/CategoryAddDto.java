package com.spharos.pointapp.category.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class CategoryAddDto {

    private Integer parentId;
    private String categoryName;

}