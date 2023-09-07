package com.spharos.pointapp.brand.infrastructure;

import com.spharos.pointapp.brand.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findAllByBrandId(Long brandId);

}
