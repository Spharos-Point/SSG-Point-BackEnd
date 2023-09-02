package com.spharos.pointapp.customer.presentation;

import com.spharos.pointapp.customer.application.CustomerService;
import com.spharos.pointapp.customer.dto.CustomerWriteDto;
import com.spharos.pointapp.customer.vo.CustomerWriteVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<String> wirteCustomer(@RequestBody CustomerWriteVo customerWriteVo){
        CustomerWriteDto customerWriteDto = CustomerWriteDto.builder()
                .categoryId(customerWriteVo.getCategoryId())
                .title(customerWriteVo.getTitle())
                .context(customerWriteVo.getContext())
                .build();
        customerService.customerWrite(customerWriteDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}