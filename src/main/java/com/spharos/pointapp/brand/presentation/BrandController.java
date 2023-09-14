package com.spharos.pointapp.brand.presentation;

import com.spharos.pointapp.brand.application.BrandService;
import com.spharos.pointapp.brand.dto.BrandAddDto;
import com.spharos.pointapp.brand.dto.BrandGetDto;
import com.spharos.pointapp.brand.vo.BrandAddInput;
import com.spharos.pointapp.brand.vo.BrandResOut;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "브랜드 등록", description = "신세계 계열사의 브랜드를 등록합니다.", tags = { "Brand Controller" })
    @PostMapping("/brand")
    public void addBrand(@RequestBody BrandAddInput brandAddInput) {
        log.info("brandAddInput: {}", brandAddInput);
        ModelMapper modelMapper = new ModelMapper();
        BrandAddDto brandAddDto = modelMapper.map(brandAddInput, BrandAddDto.class);
        brandService.addStore(brandAddDto);
    }


    @Operation(summary = "브랜드 조회", description = "신세계 계열사의 브랜드를 조회합니다.", tags = { "Brand Controller" })
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<BrandResOut> getBrand(@PathVariable("brandId") Long brandId) {
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
