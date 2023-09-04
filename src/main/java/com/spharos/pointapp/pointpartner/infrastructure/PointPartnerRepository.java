package com.spharos.pointapp.pointpartner.infrastructure;

import com.spharos.pointapp.pointpartner.domain.PointPartner;

import java.util.List;

public interface PointPartnerRepository {

    List<PointPartner> findByPointCard(String point_card);
}
