package com.spharos.pointapp.userpoint.gift.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointGiftTypeConverter implements AttributeConverter<PointGiftType, String> {


    @Override
    public String convertToDatabaseColumn(PointGiftType attribute) {
        return attribute.getCode();
    }

    @Override
    public PointGiftType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PointGiftType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}

