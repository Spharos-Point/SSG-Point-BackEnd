package com.spharos.pointapp.point.presentation;

import com.spharos.pointapp.config.security.JwtTokenProvider;
import com.spharos.pointapp.point.application.PointService;
import com.spharos.pointapp.point.dto.PointAddDto;
import com.spharos.pointapp.point.dto.PointGetDto;
import com.spharos.pointapp.point.vo.PointInVo;
import com.spharos.pointapp.point.vo.PointOutVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final JwtTokenProvider jwtTokenProvider;
    private final PointService pointService;
    private final ModelMapper modelMapper;

    /**
     * 1. 토탈 포인트 조회
     * 2. 포인트 일반 적립/사용
     * 3. 포인트 전체 조회
     * 4. 포인트 적립 조회
     * 5. 포인트 사용 조회
     */


    // 1. 토탈 포인트 조회
    @GetMapping("/pointRead/total")
    public ResponseEntity<Integer> getTotalPointByUser(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));

        Integer PointTotal = pointService.getPointTotalByUser(uuid);
        return new ResponseEntity<>(PointTotal, HttpStatus.OK);
    }

    //  2. 포인트 일반 적립/사용
    @PostMapping("/point/general")
    void addPoint(@RequestHeader("Authorization") String token,
                  @RequestBody PointInVo pointInVo) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        PointAddDto pointAddDto = modelMapper.map(pointInVo, PointAddDto.class);
        log.info("INPUT Object Data is : {} " , pointAddDto);
//        PointAddDto pointAddDto = PointAddDto.builder()
//                .pointType(pointInVo.getPointType())
//                .updatePoint(pointInVo.getUpdatePoint())
//                .used(pointInVo.getUsed())
//                .build();
        pointService.createPoint(pointAddDto, uuid);
    }


    //  3. 포인트 전체 조회
    @GetMapping("/pointRead")
    public ResponseEntity<List<PointOutVo>> getPointByUser(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.getUuid(token.substring(7));
        List<PointGetDto> pointListByUser = pointService.getPointByUser(uuid);
        log.info("INPUT Object Data is pointListByUser : {} " , pointListByUser);
        List<PointOutVo> pointOutList = pointListByUser.stream()
                .map(pointGetDto -> modelMapper.map(pointGetDto, PointOutVo.class))
                .toList();
        log.info("INPUT Object Data is pointOutList : {} " , pointOutList);
        return new ResponseEntity<>(pointOutList, HttpStatus.OK);
        }
    }

//    public List<PointOutVo> getPointByUser(@RequestHeader("Authorization") String token) {
//        String uuid = jwtTokenProvider.getUuid(token.substring(7));
//        List<PointGetDto> pointListByUser = pointService.getPointByUser(uuid);
//
//        // ModelMapper를 사용하여 PointGetDto를 PointOutVo로 변환
//        List<PointOutVo> pointOutList = pointListByUser.stream()
//                .map(pointGetDto -> modelMapper.map(pointGetDto, PointOutVo.class))
//                .collect(Collectors.toList());
//
//        return pointOutList;
//    }

//    @GetMapping("/point/all")
//    public List<Point> getAllPoint() {
//        return pointService.getAllPoint();
//    }

