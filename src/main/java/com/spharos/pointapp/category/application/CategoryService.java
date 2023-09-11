package com.spharos.pointapp.category.application;

import com.spharos.pointapp.category.dto.CategoryAddDto;
import com.spharos.pointapp.category.dto.CategoryGetDto;

import java.util.List;

public interface CategoryService {

    void addCategory(CategoryAddDto categoryAddDto);
    List<CategoryGetDto> getCategoryList();
    List<CategoryGetDto> getCategoryListByParentId(Integer parentId);
    List<CategoryGetDto> getCategoryByNotParentId();

}
