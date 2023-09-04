package com.spharos.pointapp.store.application;

import com.spharos.pointapp.store.domain.Branch;
import com.spharos.pointapp.store.dto.BranchAddDto;
import com.spharos.pointapp.store.dto.BranchGetDto;
import com.spharos.pointapp.store.infrastructure.BranchRepository;
import com.spharos.pointapp.store.infrastructure.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BranchServiceImple implements BranchService{

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;

    @Override
    public void addBranch(BranchAddDto branchAddDto) {
        Branch branch = Branch.builder()
                .branchName(branchAddDto.getBranchName())
                .address(branchAddDto.getAddress())
                .phone(branchAddDto.getPhone())
                .store(storeRepository.findById(branchAddDto.getStoreId()).get())
                .build();
    }

    @Override
    public BranchGetDto getBranchById(Long id) {
        Branch branch = branchRepository.findById(id).get();
        BranchGetDto branchGetDto = BranchGetDto.builder()
                .id(branch.getId())
                .branchName(branch.getBranchName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .store(storeRepository.findById(branch.getStore().getId()).get())
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
                        .store(storeRepository.findById(branch.getStore().getId()).get())
                        .build()
        ).toList();
        return branchGetDtoList;
    }

    @Override
    public List<BranchGetDto> getBranchListByStoreId(Long storeId) {
        List<Branch> branchList = branchRepository.findAllByStoreId(storeId);
        List<BranchGetDto> branchGetDtoList = branchList.stream().map(
                branch -> BranchGetDto.builder()
                        .id(branch.getId())
                        .branchName(branch.getBranchName())
                        .address(branch.getAddress())
                        .phone(branch.getPhone())
                        .store(storeRepository.findById(branch.getStore().getId()).get())
                        .build()
        ).toList();
        return branchGetDtoList;
    }
}
