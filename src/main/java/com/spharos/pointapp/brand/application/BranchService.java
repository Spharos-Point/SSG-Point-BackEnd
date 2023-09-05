package com.spharos.pointapp.brand.application;

import com.spharos.pointapp.brand.dto.BranchAddDto;
import com.spharos.pointapp.brand.dto.BranchGetDto;

import java.util.List;

public interface BranchService {

    void addBranch(BranchAddDto branchAddDto);
    BranchGetDto getBranchById(Long id);
    List<BranchGetDto> getBranchList();
    List<BranchGetDto> getBranchListByStoreId(Long brandId);

}
