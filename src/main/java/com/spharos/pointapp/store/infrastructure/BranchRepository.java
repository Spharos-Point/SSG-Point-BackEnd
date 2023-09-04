package com.spharos.pointapp.store.infrastructure;

import com.spharos.pointapp.store.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findAllByStoreId(Long storeId);

}
