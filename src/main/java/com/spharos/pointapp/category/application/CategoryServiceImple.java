package com.spharos.pointapp.category.application;

import com.spharos.pointapp.category.domain.Category;
import com.spharos.pointapp.category.dto.CategoryAddDto;
import com.spharos.pointapp.category.dto.CategoryGetDto;
import com.spharos.pointapp.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImple implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(CategoryAddDto categoryAddDto) {

        log.info("{}", categoryAddDto);
        if(categoryAddDto.getParentId() == null){
            Category category = Category.builder()
                    .categoryName(categoryAddDto.getCategoryName())
                    .build();
            categoryRepository.save(category);
        } else {

            Category parentCategory = categoryRepository.findById(categoryAddDto.getParentId()).get();
            Category category = Category.builder()
                    .categoryName(categoryAddDto.getCategoryName())
                    .parentCategory(parentCategory)
                    .build();

            categoryRepository.save(category);
        }
    }

    @Override
    public List<CategoryGetDto> getCategoryList() {
        return null;
    }

    @Override
    public List<CategoryGetDto> getCategoryListByParentId(Integer parentId) {
        List<Category> categoryList = categoryRepository.findByParentCategory(categoryRepository.findById(parentId).get());
        List<CategoryGetDto> categoryGetDtoList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                    Category category1 = categoryRepository.findById(category.getId()).get();
                    CategoryGetDto categoryGetDto = CategoryGetDto.builder()
                            .categoryId(category1.getId())
                            .categoryName(category1.getCategoryName())
                            .build();
                    categoryGetDtoList.add(categoryGetDto);
                }
        );
        return categoryGetDtoList;
    }

    @Override
    public List<CategoryGetDto> getCategoryByNotParentId() {
        List<Category> categoryList = categoryRepository.findByParentCategoryIsNull();
        log.info("{}", categoryList);
        List<CategoryGetDto> categoryGetDtoList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                    Category category1 = categoryRepository.findById(category.getId()).get();
                    CategoryGetDto categoryGetDto = CategoryGetDto.builder()
                            .categoryId(category1.getId())
                            .categoryName(category1.getCategoryName())
                            .build();
                    categoryGetDtoList.add(categoryGetDto);
                }
        );
        return categoryGetDtoList;
    }
}
