package com.spharos.pointapp.brand.application;

import com.spharos.pointapp.brand.domain.Branch;
import com.spharos.pointapp.brand.dto.BranchAddDto;
import com.spharos.pointapp.brand.dto.BranchGetDto;
import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.brand.infrastructure.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BranchServiceImple implements BranchService{

    private final BranchRepository branchRepository;
    private final BrandRepository brandRepository;

    @Override
    public void addBranch(BranchAddDto branchAddDto) {
        Branch branch = Branch.builder()
                .branchName(branchAddDto.getBranchName())
                .address(branchAddDto.getAddress())
                .phone(branchAddDto.getPhone())
                .brand(brandRepository.findById(branchAddDto.getBrandId()).get())
                .build();
        branchRepository.save(branch);
        log.info("{}", branch);
    }

    @Override
    public BranchGetDto getBranchById(Long id) {
        Branch branch = branchRepository.findById(id).get();
        BranchGetDto branchGetDto = BranchGetDto.builder()
                .id(branch.getId())
                .branchName(branch.getBranchName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .brand(brandRepository.findById(branch.getBrand().getId()).get())
                .build();

        return branchGetDto;
    }

    @Override
    public List<BranchGetDto> getBranchList() {
        List<Branch> branchList = branchRepository.findAll();
        List<BranchGetDto> branchGetDtoList = branchList.stream().map(
                branch -> BranchGetDto.builder()
                        .id(branch.getId())
                        .branchName(branch.getBranchName())
                        .address(branch.getAddress())
                        .phone(branch.getPhone())
                        .brand(brandRepository.findById(branch.getBrand().getId()).get())
                        .build()
        ).toList();
        return branchGetDtoList;
    }

    @Override
    public List<BranchGetDto> getBranchListByStoreId(Long storeId) {
        List<Branch> branchList = branchRepository.findAllByBrandId(storeId);
        List<BranchGetDto> branchGetDtoList = branchList.stream().map(
                branch -> BranchGetDto.builder()
                        .id(branch.getId())
                        .branchName(branch.getBranchName())
                        .address(branch.getAddress())
                        .phone(branch.getPhone())
                        .brand(brandRepository.findById(branch.getBrand().getId()).get())
                        .build()
        ).toList();
        return branchGetDtoList;
    }

}
