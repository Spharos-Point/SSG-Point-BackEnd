package com.spharos.pointapp.brand.infrastructure;

import com.spharos.pointapp.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long>{

//    List<Brand> findAllByBrandId(Long brandId);
}
