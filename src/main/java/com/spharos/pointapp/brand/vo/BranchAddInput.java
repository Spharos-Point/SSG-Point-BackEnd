package com.spharos.pointapp.brand.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchAddInput {

    private String branchName;
    private String address;
    private String phone;
    private String latitude;
    private String longitude;
    private Long brandId;


}
