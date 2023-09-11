package com.spharos.pointapp.pointcard.presentation;

import com.spharos.pointapp.brand.infrastructure.BranchRepository;
import com.spharos.pointapp.pointcard.application.PointCardService;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.vo.PointCardCreateInVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class PointCardController {

    private final PointCardService pointCardService;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;

    //    1.포인트 카드 생성
    @PostMapping("/mypoint/regPntCard")
    public void createPointCard(@RequestBody PointCardCreateInVo pointCardCreateInVo,
                                @RequestHeader("uuid") String uuid) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .branchId(pointCardCreateInVo.getBranchId())
                .cardNumber(pointCardCreateInVo.getCardNumber())
                .cvc(pointCardCreateInVo.getCvc())
                .brandId(pointCardCreateInVo.getBrandId())
                .uuid(uuid)
                .build();
        pointCardService.createPointCard(pointCardCreateDto);
    }

    //    2. 포인트 카드 목록
//    @GetMapping("/mypoint/cardManage")
//    public ResponseEntity<List<PointCardOutVo>> getPointCardByUser(@RequestHeader("uuid") String uuid) {
//        return ResponseEntity.ok(modelMapper.map(pointCardService.getPointCardByUser(uuid), List.class));
//    }

}