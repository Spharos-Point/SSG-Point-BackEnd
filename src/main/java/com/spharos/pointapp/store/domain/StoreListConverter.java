package com.spharos.pointapp.store.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class StoreListConverter implements AttributeConverter<StoreList, String> {
    @Override
    public String convertToDatabaseColumn(StoreList attribute) { return attribute.getCode(); }

    @Override
    public StoreList convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(StoreList.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}
