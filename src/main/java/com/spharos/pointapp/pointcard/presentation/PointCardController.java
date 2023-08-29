package com.spharos.pointapp.pointcard.presentation;

import com.spharos.pointapp.pointcard.application.PointCardService;
import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
import com.spharos.pointapp.pointcard.vo.PointCardCreateInVo;
import com.spharos.pointapp.user.dto.UserUpdateInfoDto;
import com.spharos.pointapp.user.vo.UserUpdateInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class PointCardController {

    @PostMapping("/mypoint/regPntCard")
    public void createPointCard(@RequestBody PointCardCreateInVo pointCardCreateInVo,
                                @RequestParam("uuid") String uuid) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .number(pointCardCreateInVo.getNumber())
                .cvc(pointCardCreateInVo.getCvc())
                .partnerName(pointCardCreateInVo.getPartnerName())
                .registeredStore(pointCardCreateInVo.getRegisteredStore())
                .uuid(uuid)
                .build();

    }

    // 포인트 카드 목록
    @GetMapping("/mypoint/cardManage")
    public List<PointCardGetVo> getPointCardByUser(@RequestParam("uuid") String uuid) {
        List<PointCardGetDto> pointCardGetDtoList = PointCardService.getPointCardByUser(uuid);

        return pointCardGetDtoList.stream().map(pointCardGetDto ->
                PointCardGetVo.builder()
                        .partnerName(pointCardGetDto.getName())
                        .number(pointCardGetDto.getNumber())
                        .agency(pointCardGetDto.getAgency())
                        .build()
        ).toList();

}
