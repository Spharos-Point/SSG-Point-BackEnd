package com.spharos.pointapp.customer.application;

import com.spharos.pointapp.customer.dto.CustomerWriteDto;
import com.spharos.pointapp.customer.infrastructure.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServicelmple implements CustomerService {

    @Override
    public void customerWrite(CustomerWriteDto customerWriteDto) {
        Integer categoryId = customerWriteDto.getCategoryId();
        String title = customerWriteDto.getTitle();
        String context = customerWriteDto.getContext();
    }
}