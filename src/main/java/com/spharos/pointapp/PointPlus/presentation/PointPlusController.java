package com.spharos.pointapp.PointPlus.presentation;

import com.spharos.pointapp.PointPlus.application.PointPlusService;
import com.spharos.pointapp.PointPlus.dto.AttendAddDto;
import com.spharos.pointapp.PointPlus.vo.AttendAdd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointPlusController {

    private final PointPlusService pointPlusService;
    private final ModelMapper modelMapper;

//    출석 포인트 적립
    @PostMapping("/benefits/pntPlus/attend/")
    void attendAdd(@RequestBody AttendAdd attendAdd,
                   @RequestHeader("uuid") String uuid) {
        log.info("INPUT Object Data is : {}" , attendAdd);
        AttendAddDto attendAddDto = AttendAddDto.builder()
                .attendCnt(attendAdd.getAttendCnt())
                .attendPoint(attendAdd.getAttendPoint())
                .uuid(uuid)
                .build();
        pointPlusService.addPointAttend(attendAddDto, uuid);
    }
}
