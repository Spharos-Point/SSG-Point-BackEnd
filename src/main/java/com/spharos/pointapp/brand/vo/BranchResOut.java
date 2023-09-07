package com.spharos.pointapp.brand.vo;

import lombok.*;
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchResOut {

    private Long id;
    private String branchName;
    private String address;
    private String phone;
    private String latitude;
    private String longitude;
    private Integer storeId;
    private String storeName;

}
