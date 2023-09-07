package com.spharos.pointapp.pointcard.presentation;

import com.spharos.pointapp.pointcard.application.PointCardService;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
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

    // 3. 포인트 카드 삭제
//    @DeleteMapping("/mypoint/cardDelete")
//    private ResponseEntity<String> deletePointCard(@RequestParam(name = "uuid", defaultValue = "") String Uuid) {
//        try {
//            // Uuid를 사용하여 포인트 카드 삭제
//            pointCardService.deletePointCard(Uuid);
//
//            // 성공 응답 반환
//            return ResponseEntity.ok("포인트 카드가 성공적으로 삭제되었습니다.");
//        } catch (Exception e) {
//            return null;
//        }
//    }
}