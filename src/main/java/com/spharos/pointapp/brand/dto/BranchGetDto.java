package com.spharos.pointapp.brand.dto;

import com.spharos.pointapp.brand.domain.Brand;
import lombok.*;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchGetDto {

    private Long id;
    private String branchName;
    private String address;
    private String phone;

    private Brand brand;

}
