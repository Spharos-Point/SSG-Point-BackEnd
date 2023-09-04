package com.spharos.pointapp.store.dto;

import com.spharos.pointapp.store.domain.Store;
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

    private Store store;

}
