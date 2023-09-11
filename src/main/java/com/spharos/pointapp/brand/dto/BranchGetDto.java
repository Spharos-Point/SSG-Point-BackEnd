package com.spharos.pointapp.brand.dto;

import com.spharos.pointapp.brand.domain.Brand;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchGetDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String address;
    private String phone;
    private String latitude;
    private String longitude;

    private Brand brand;

}
