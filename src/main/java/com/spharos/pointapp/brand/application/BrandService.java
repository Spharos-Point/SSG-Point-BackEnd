package com.spharos.pointapp.brand.application;

import com.spharos.pointapp.brand.dto.BrandAddDto;
import com.spharos.pointapp.brand.dto.BrandGetDto;

import java.util.List;

public interface BrandService {

    void addStore(BrandAddDto brandAddDto);
    BrandGetDto getBrandById(Integer id);
    List<BrandGetDto> getAllBrandList();
    List<BrandGetDto> getBrandByBrandName(String brandName);

}
