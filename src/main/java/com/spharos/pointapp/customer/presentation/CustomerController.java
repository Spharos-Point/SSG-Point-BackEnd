package com.spharos.pointapp.customer.presentation;

import com.spharos.pointapp.customer.application.CustomerService;
import com.spharos.pointapp.customer.dto.CustomerWriteDto;
import com.spharos.pointapp.customer.vo.CustomerWriteVo;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "고객상담 게시글 작성", description = "고객상담 게시글을 작성합니다.", tags = { "Customer Controller" })
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