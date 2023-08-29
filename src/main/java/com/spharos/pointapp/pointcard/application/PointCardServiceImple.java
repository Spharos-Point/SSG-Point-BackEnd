package com.spharos.pointapp.pointcard.application;

import com.spharos.pointapp.pointcard.infrastructure.PointCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointCardServiceImple implements PointCardService {

    private final PointCardRepository pointCardRepository;

    /**
     *
     * 1. 포인트 카드 적립
     *
     */

}