package com.spharos.pointapp.userpoint.trans.application;

import com.spharos.pointapp.userpoint.trans.dto.PointTransDto;
import com.spharos.pointapp.userpoint.trans.presentation.PointTransService;
import com.spharos.pointapp.userpoint.trans.vo.PointTransAddRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PointTransController {

    private final PointTransService pointTransService;
    private final ModelMapper modelMapper;

    @PostMapping("/point-trans")
    public void transPoint(
            @RequestBody PointTransAddRequest pointTransAddRequest
    ) {
        log.info("transPoint");
        pointTransService.transPoint(
                modelMapper.map(pointTransAddRequest, PointTransDto.class)
        );
    }
}
