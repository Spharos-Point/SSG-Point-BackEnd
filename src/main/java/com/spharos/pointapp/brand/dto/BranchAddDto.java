package com.spharos.pointapp.brand.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BranchAddDto {

    private String branchName;
    private String address;
    private String phone;

    private Integer brandId;

}
