package com.spharos.pointapp.customer.vo;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CustomerWriteVo {
    private Integer categoryId;
    private String title;
    private String context;
}

