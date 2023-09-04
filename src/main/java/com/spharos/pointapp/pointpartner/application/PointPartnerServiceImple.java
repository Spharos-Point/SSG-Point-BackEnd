package com.spharos.pointapp.pointpartner.application;

import com.spharos.pointapp.pointpartner.domain.PointPartner;
import com.spharos.pointapp.pointpartner.dto.PointPartnerOutDto;
import com.spharos.pointapp.pointpartner.infrastructure.PointPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class PointPartnerServiceImple implements PointPartnerService {
//
//    private final PointPartnerRepository pointPartnerRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public  List<PointPartnerOutDto> getPointCardByPointCard(String pointcardid) {
//        List<PointPartner> pointPartner = pointPartnerRepository.findByPointCard(pointcardid);
//
//        List<PointPartnerOutDto> pointPartnerOutDto = pointPartner.stream().map(
//                pointPartner1 -> modelMapper.map(pointPartner, PointPartnerOutDto.class)
//        ).toList();
//        return pointPartnerOutDto;
//    }


}
