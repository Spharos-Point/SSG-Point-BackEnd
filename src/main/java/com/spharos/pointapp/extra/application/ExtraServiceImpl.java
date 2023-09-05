package com.spharos.pointapp.extra.application;

import com.spharos.pointapp.extra.domain.Extra;
import com.spharos.pointapp.extra.dto.ExtraAddDto;
import com.spharos.pointapp.extra.dto.ExtraUpdateDto;
import com.spharos.pointapp.extra.infrastructure.ExtraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExtraServiceImpl implements ExtraService {

    private final ExtraRepository extraRepository;

    @Override
    public void createExtra(ExtraAddDto extraAddDto) {
        extraRepository.save(
                Extra.builder()
                        .extraName(extraAddDto.getExtraName())
                        .build());
    }

    @Override
    public void updateExtra(ExtraUpdateDto extraUpdateDto, Long extraId) {
        log.info("{}", extraId);
        Extra extra = extraRepository.findById(extraId).get();
        extraRepository.save(
                Extra.builder()
                        .Id(extraId)
                        .extraName(extraUpdateDto.getExtraName())
                        .build()
        );
        log.info("{}", extra);
    }

    @Override
    public void deleteExtra(Long extraId) {
        extraRepository.deleteById(extraId);
    }
}

