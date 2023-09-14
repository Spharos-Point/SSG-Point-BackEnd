package com.spharos.pointapp.userpoint.pointList.presentation;

import com.spharos.pointapp.config.common.BaseException;
import com.spharos.pointapp.config.common.BaseResponse;
import com.spharos.pointapp.userpoint.pointList.application.UserPointListService;
import com.spharos.pointapp.userpoint.pointList.dto.UserPointListResDto;
import com.spharos.pointapp.userpoint.pointList.vo.UserPointResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserPointListController {

    private final UserPointListService userPointListService;
//    private final TokenUtils tokenUtils;

    @GetMapping("/userPointList/{uuid}")
    public BaseResponse<List<UserPointResVo>> getUserPointList(
            @PathVariable("uuid") String uuid
    ) {
        try {
            List<UserPointListResDto> userPointList = userPointListService.getUserPointListByUuid(uuid);
            log.info("userPointList : {}", userPointList);
            List<UserPointResVo> userPointResVoList = new ArrayList<>();
            userPointList.forEach(
                    userPointListResDto -> {
                        userPointResVoList.add(
                        UserPointResVo.builder()
                                .id(userPointListResDto.getId())
                                .pointType(userPointListResDto.getPointType())
                                .pointId(userPointListResDto.getPointId())
                                .pointTypeById(userPointListResDto.getPointTypeById())
                                .createAt(userPointListResDto.getCreateAt())
                                .updateAt(userPointListResDto.getUpdateAt())
                                .build());
                    });

            return new BaseResponse<>(userPointResVoList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
