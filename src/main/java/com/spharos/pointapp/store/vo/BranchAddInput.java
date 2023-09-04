package com.spharos.pointapp.store.vo;

import lombok.*;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchAddInput {

    private String branchName;
    private String address;
    private String phone;
    private Integer storeId;

}
