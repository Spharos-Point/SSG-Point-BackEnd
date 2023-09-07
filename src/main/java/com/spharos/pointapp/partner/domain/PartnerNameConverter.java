package com.spharos.pointapp.partner.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PartnerNameConverter implements AttributeConverter<PartnerName, String> {
    @Override
    public String convertToDatabaseColumn(PartnerName attribute) { return attribute.getCode(); }

    @Override
    public PartnerName convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PartnerName.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}
