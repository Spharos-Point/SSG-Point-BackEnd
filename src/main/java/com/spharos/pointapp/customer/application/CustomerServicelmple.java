package com.spharos.pointapp.customer.application;

import com.spharos.pointapp.customer.domain.Customer;
import com.spharos.pointapp.customer.dto.CustomerWriteDto;
import com.spharos.pointapp.customer.infrastructure.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServicelmple implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void customerWrite(CustomerWriteDto customerWriteDto) {

//        todo: transactional
//        Customer customer = Customer.builder()
//                .categoryId(customerWriteDto.getCategoryId())
//                .title(customerWriteDto.getTitle())
//                .context(customerWriteDto.getContext())
//                .build();
//        customerRepository.save(customer);

    }
}