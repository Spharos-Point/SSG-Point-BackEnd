package com.spharos.pointapp.brand.presentation;

import com.spharos.pointapp.brand.application.BranchService;
import com.spharos.pointapp.brand.application.BrandService;
import com.spharos.pointapp.brand.dto.BranchAddDto;
import com.spharos.pointapp.brand.dto.BranchGetDto;
import com.spharos.pointapp.brand.dto.BrandGetDto;
import com.spharos.pointapp.brand.vo.BranchAddInput;
import com.spharos.pointapp.brand.vo.BranchResOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class BranchController {

    private final BranchService branchService;
    private final BrandService brandService;

    @PostMapping("/branch")
    public void addBranch(@RequestBody BranchAddInput branchAddInput) {
        log.info("branchAddInput: {}", branchAddInput);
        ModelMapper modelMapper = new ModelMapper();
        BranchAddDto branchAddDto = modelMapper.map(branchAddInput, BranchAddDto.class);
        branchService.addBranch(branchAddDto);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<BranchResOut> getBranch(@PathVariable("branchId") Long branchId) {
        BranchGetDto branchGetDto = branchService.getBranchById(branchId);
        log.info("branchGetDto: {}", branchGetDto);
        BranchResOut branchResOut = BranchResOut.builder()
                .id(branchGetDto.getId())
                .branchName(branchGetDto.getBranchName())
                .address(branchGetDto.getAddress())
                .phone(branchGetDto.getPhone())
                .brand(brandRepositoty.findById(branch.getBrand().getId()).get())
                .brandName(brandGetDto.getBrandName())
                .build();
        return ResponseEntity.of(java.util.Optional.of(branchResOut));
    }
}