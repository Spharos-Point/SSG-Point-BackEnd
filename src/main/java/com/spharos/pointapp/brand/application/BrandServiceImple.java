package com.spharos.pointapp.brand.application;

import com.spharos.pointapp.brand.domain.Brand;
import com.spharos.pointapp.brand.dto.BrandAddDto;
import com.spharos.pointapp.brand.dto.BrandGetDto;
import com.spharos.pointapp.brand.infrastructure.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrandServiceImple implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public void addStore(BrandAddDto brandAddDto) {
        brandRepository.save(Brand.builder()
                .brandName(brandAddDto.getBrandName())
                .logoImage(brandAddDto.getLogoImage())
                .build());
    }

    @Override
    public BrandGetDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).get();
        log.info("brand: {}", brand);
        ModelMapper modelMapper = new ModelMapper();
        BrandGetDto brandGetDto = BrandGetDto.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .logoImage(brand.getLogoImage())
                .build();
        return brandGetDto;
    }


    @Override
    public List<BrandGetDto> getBrandByBrandName(String brandName) {
        return null;
    }

    @Override
    public List<BrandGetDto> getAllBrandList() {
        ModelMapper modelMapper = new ModelMapper();
        List<BrandGetDto> brandGetDtoList = brandRepository.findAll().stream()
                .map(brand -> modelMapper.map(brand, BrandGetDto.class))
                .toList();
        return brandGetDtoList;
    }

}
