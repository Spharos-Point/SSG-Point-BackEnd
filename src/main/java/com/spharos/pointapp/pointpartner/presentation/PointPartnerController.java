package com.spharos.pointapp.pointpartner.presentation;

import com.spharos.pointapp.pointpartner.application.PointPartnerService;
import com.spharos.pointapp.pointpartner.vo.PointPartnerOutVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
@RequiredArgsConstructor
public class PointPartnerController {

    private final PointPartnerService pointPartnerService;
    private final ModelMapper modelMapper;

    @GetMapping("/pointpartner")
    public ResponseEntity<List<PointPartnerOutVo>> getPointPartnerByPointCard(@RequestHeader("pointCard")String pointcard) {
        return ResponseEntity.ok(modelMapper.map(pointPartnerService.getPointPartnerByPointCard(pointcard),List.class));
    }
}
