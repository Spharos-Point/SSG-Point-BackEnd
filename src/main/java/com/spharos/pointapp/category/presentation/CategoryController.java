package com.spharos.pointapp.category.presentation;

import com.spharos.pointapp.category.application.CategoryService;
import com.spharos.pointapp.category.dto.CategoryAddDto;
import com.spharos.pointapp.category.dto.CategoryGetDto;
import com.spharos.pointapp.category.vo.CategoryAdd;
import com.spharos.pointapp.category.vo.CategoryGet;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    //    1:1 상담, FAQ 카테고리 생성
    @Operation(summary = "카테고리 생성", description = "게시판의 카테고리를 생성합니다.", tags = { "Category Controller" })
    @PostMapping("/category")
    public void addCategory(@RequestBody CategoryAdd categoryAdd) {

        ModelMapper modelMapper = new ModelMapper();
        CategoryAddDto categoryAddDto = modelMapper.map(categoryAdd, CategoryAddDto.class);
        categoryService.addCategory(categoryAddDto);
    }

    //    1:1 상담, FAQ 카테고리 조회
    @Operation(summary = "카테고리 조회", description = "게시판의 카테고리를 조회합니다.", tags = { "Category Controller" })
    @GetMapping("/category/parent")
    public List<CategoryGet> getCategoryByNotParentId() {
        List<CategoryGetDto> categoryGetDtos = categoryService.getCategoryByNotParentId();
        List<CategoryGet> categoryGets = new ArrayList<>();
        categoryGetDtos.forEach(
                categoryGetDto -> {
                    log.info("{}", categoryGetDto);
                    categoryGets.add(CategoryGet.builder()
                                    .categoryId(categoryGetDto.getCategoryId())
                                    .categoryName(categoryGetDto.getCategoryName())
                            .build());
                }
        );
        return categoryGets;
    }

    @Operation(summary = "카테고리 소분류", description = "게시판의 카테고리 소분류를 조회합니다.", tags = { "Category Controller" })
    @GetMapping("/category/{parentId}")
    public List<CategoryGet> getCategoryByParentId(@PathVariable("parentId") Integer parentId) {
        List<CategoryGetDto> categoryGetDtos = categoryService.getCategoryListByParentId(parentId);
        List<CategoryGet> categoryGets = new ArrayList<>();
        categoryGetDtos.forEach(
                categoryGetDto -> {
                    log.info("{}", categoryGetDto);
                    categoryGets.add(CategoryGet.builder()
                            .categoryId(categoryGetDto.getCategoryId())
                            .categoryName(categoryGetDto.getCategoryName())
                            .build());
                }
        );
        return categoryGets;
    }

}