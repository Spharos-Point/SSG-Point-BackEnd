//package com.spharos.pointapp.coupon.domain;
//
//import jakarta.persistence.AttributeConverter;
//
//import java.util.EnumSet;
//import java.util.NoSuchElementException;
//
//public class UsePlaceConverter implements AttributeConverter<UsePlace, String> {
//    @Override
//    public String convertToDatabaseColumn(CouponType attribute) {
//        return attribute.getCode();
//    }
//
//    @Override
//    public UsePlace convertToEntityAttribute(String dbData) {
//        return EnumSet.allOf(UsePlace.class).stream()
//                .filter(c -> c.getCode().equals(dbData))
//                .findFirst()
//                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
//}
