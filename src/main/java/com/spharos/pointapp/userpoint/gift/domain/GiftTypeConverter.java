package com.spharos.pointapp.userpoint.gift.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class GiftTypeConverter implements AttributeConverter<GiftType, String> {


    @Override
    public String convertToDatabaseColumn(GiftType attribute) {
        return attribute.getCode();
    }

    @Override
    public GiftType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(GiftType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}

