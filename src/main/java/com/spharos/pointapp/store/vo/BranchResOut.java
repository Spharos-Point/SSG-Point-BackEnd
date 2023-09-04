package com.spharos.pointapp.store.vo;

import lombok.*;

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
    private Integer storeId;
    private String storeName;

}
