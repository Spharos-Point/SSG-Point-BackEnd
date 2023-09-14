package com.spharos.pointapp.brand.presentation;

import com.spharos.pointapp.brand.application.BrandService;
import com.spharos.pointapp.brand.dto.BrandAddDto;
import com.spharos.pointapp.brand.dto.BrandGetDto;
import com.spharos.pointapp.brand.vo.BrandAddInput;
import com.spharos.pointapp.brand.vo.BrandResOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/brand")
    public void addBrand(@RequestBody BrandAddInput brandAddInput) {
        log.info("brandAddInput: {}", brandAddInput);
        ModelMapper modelMapper = new ModelMapper();
        BrandAddDto brandAddDto = modelMapper.map(brandAddInput, BrandAddDto.class);
        brandService.addStore(brandAddDto);
    }


    @GetMapping("/brand/{brandId}")
    public ResponseEntity<BrandResOut> getBrand(@PathVariable("brandId") Integer brandId) {
        BrandGetDto brandGetDto = brandService.getBrandById(brandId);
        log.info("brandGetDto: {}", brandGetDto);
        BrandResOut brandResOut = BrandResOut.builder()
                .id(brandGetDto.getId())
                .brandName(brandGetDto.getBrandName())
                .logoImage(brandGetDto.getLogoImage())
                .build();
        return ResponseEntity.of(java.util.Optional.of(brandResOut));
    }
}
