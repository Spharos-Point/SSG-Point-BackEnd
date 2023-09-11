package com.spharos.pointapp.extra.application;

import com.spharos.pointapp.extra.dto.ExtraAddDto;
import com.spharos.pointapp.extra.dto.ExtraUpdateDto;

public interface ExtraService {

//    외부 제휴사 생성
    void createExtra(ExtraAddDto extraAddDto);

//    외부 제휴사 수정
    void updateExtra(ExtraUpdateDto extraUpdateDto, Long extraId);

//    외부 제휴사 삭제
    void deleteExtra(Long extraId);
}
