package com.spharos.pointapp.pointpartner.application;

import com.spharos.pointapp.pointpartner.dto.PointPartnerOutDto;

import java.util.List;

public interface PointPartnerService {

    List<PointPartnerOutDto> getPointPartnerByPointCard(String pointcardid);
}
