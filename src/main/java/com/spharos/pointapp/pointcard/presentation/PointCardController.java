package com.spharos.pointapp.pointcard.presentation;

import com.spharos.pointapp.pointcard.application.PointCardService;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
import com.spharos.pointapp.pointcard.vo.PointCardCreateInVo;
import com.spharos.pointapp.pointcard.vo.PointCardOutVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class PointCardController {

    private final PointCardService pointCardService;
    private final ModelMapper modelMapper;

    //    1.포인트 카드 생성
    @PostMapping("/mypoint/regPntCard")
    public void createPointCard(@RequestBody PointCardCreateInVo pointCardCreateInVo,
                                @RequestHeader("uuid") String uuid) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .barcode(pointCardCreateInVo.getBarcode())
                .cvc(pointCardCreateInVo.getCvc())
                .partnerName(pointCardCreateInVo.getPartnerName())
                .registeredStore(pointCardCreateInVo.getRegisteredStore())
                .uuid(uuid)
                .build();
        pointCardService.createPointCard(pointCardCreateDto);
    }

    //    2. 포인트 카드 목록
    @GetMapping("/mypoint/cardManage")
    public ResponseEntity<List<PointCardOutVo>> getPointCardByUser(@RequestHeader("uuid") String uuid) {
        return ResponseEntity.ok(modelMapper.map(pointCardService.getPointCardByUser(uuid), List.class));
    }


//    public List<PointCardOutVo> getPointCardByUser(@RequestHeader("uuid") String uuid) {
//        List<PointCardOutDto> pointCardGetDtoList = PointCardService.getPointCardByUser(uuid);
//
//        return pointCardGetDtoList.stream().map(pointCardOutDto ->
//                PointCardOutVo.builder()
//                        .barcode(pointCardOutDto.getBarcode())
//                        .partnerName(pointCardOutDto.getPartnerName())
//                        .createAt(pointCardOutDto.getCreateAt())
//                        .build()
//        ).toList();
//
//    }
}
