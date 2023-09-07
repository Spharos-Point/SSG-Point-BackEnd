package com.spharos.pointapp.brand.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BranchAddDto {

    private String branchName;
    private String address;
    private String phone;
    private String latitude;
    private String longitude;

    private Integer brandId;

}
