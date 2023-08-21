package com.spharos.pointapp.customer.infrastructure;

import com.spharos.pointapp.customer.domain.CustomerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCategoryRepository extends JpaRepository<CustomerCategory, Integer> {
}
