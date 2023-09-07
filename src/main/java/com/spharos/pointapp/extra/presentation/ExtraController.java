package com.spharos.pointapp.extra.presentation;

import com.spharos.pointapp.event.dto.EventUpdateDto;
import com.spharos.pointapp.extra.application.ExtraService;
import com.spharos.pointapp.extra.dto.ExtraAddDto;
import com.spharos.pointapp.extra.dto.ExtraUpdateDto;
import com.spharos.pointapp.extra.vo.ExtraAdd;
import com.spharos.pointapp.extra.vo.ExtraUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ExtraController {

    private final ExtraService extraService;

//    외부 제휴사 생성
    @PostMapping("/extra")
    public void createExtra(@RequestBody ExtraAdd extraAdd) {
        log.info("{}", extraAdd);
        ModelMapper mapper = new ModelMapper();
        ExtraAddDto extraAddDto = mapper.map(extraAdd, ExtraAddDto.class);
        extraService.createExtra(extraAddDto);
    }

//    외부 제휴사 수정
    @PutMapping("/extra")
    public void updateExtra(@RequestBody ExtraUpdate extraUpdate) {
        log.info("{}", extraUpdate);
        ModelMapper mapper = new ModelMapper();
        ExtraUpdateDto extraUpdateDto = mapper.map(extraUpdate, ExtraUpdateDto.class);
        extraService.updateExtra(extraUpdateDto, extraUpdate.getExtraId());
    }

//    외부 제휴사 삭제
    @DeleteMapping("/extra")
    private void deleteExtra(@RequestParam(name = "extraId", defaultValue = "") Long extraId) {
        extraService.deleteExtra(extraId);
    }
}
