package com.spharos.pointapp.brand.infrastructure;

import com.spharos.pointapp.brand.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findAllByBrandId(Long brandId);
    Optional<Branch> findById(Long Id);


}
