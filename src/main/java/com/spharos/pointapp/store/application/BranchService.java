package com.spharos.pointapp.store.application;

import com.spharos.pointapp.store.dto.BranchAddDto;
import com.spharos.pointapp.store.dto.BranchGetDto;

import java.util.List;

public interface BranchService {

    void addBranch(BranchAddDto branchAddDto);
    BranchGetDto getBranchById(Long id);
    List<BranchGetDto> getBranchList();
    List<BranchGetDto> getBranchListByStoreId(Long storeId);

}
