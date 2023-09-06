package com.spharos.pointapp.brand.infrastructure;

import com.spharos.pointapp.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

    List<Brand> findAllByBrandId(Long brandId);
}
