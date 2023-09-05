package com.spharos.pointapp.PointPlus.application;

import com.spharos.pointapp.PointPlus.domain.Attend;
import com.spharos.pointapp.PointPlus.dto.AttendAddDto;
import com.spharos.pointapp.PointPlus.infrastructure.AttendRepository;
import com.spharos.pointapp.user.domain.User;
import com.spharos.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointPlusServiceImpl implements PointPlusService {

    private final AttendRepository attendRepository;
    private final UserRepository userRepository;

    @Override
    public void addPointAttend(AttendAddDto attendAddDto, String uuid) {
        log.info("AttendAddDto is : {}" , attendAddDto);
        User user = userRepository.findByUuid(uuid).orElseThrow(() ->
                new IllegalArgumentException("해당하는 uuid가 없습니다. " + uuid));

        attendRepository.save(Attend.builder()
                .attendCnt(attendAddDto.getAttendCnt())
                .attendPoint(attendAddDto.getAttendPoint())
                .user(user)
                .build());
    }
}


