package com.spharos.pointapp.customer.infrastructure;

import com.spharos.pointapp.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
