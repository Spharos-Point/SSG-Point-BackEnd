package com.spharos.pointapp.category.infrastructure;

import com.spharos.pointapp.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

//    List<Category> findByParentId(Integer parentId);
    @Query(value = "SELECT * FROM category where parent_id is null", nativeQuery = true)
    List<Category> findByParentCategoryIsNull();
    List<Category> findByParentCategory(Category parentCategory);

}
