package com.spharos.pointapp.store.dto;

import com.spharos.pointapp.store.domain.Store;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BranchAddDto {

    private Long id;
    private String branchName;
    private String address;
    private String phone;

    private Integer storeId;

}
