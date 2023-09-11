package com.spharos.pointapp.admin.administrator.application;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.admin.administrator.domain.Roll;
import com.spharos.pointapp.admin.administrator.dto.AdministratorCreateDto;
import com.spharos.pointapp.admin.administrator.infrastructure.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdministratorServiceImple implements AdministratorService{
    private final AdministratorRepository administratorRepository;
    @Override
    public void createAdministrator(AdministratorCreateDto administratorCreateDto) {

        log.info("{}", administratorCreateDto);
        Roll roll = Roll.valueOf(administratorCreateDto.getRoll());
        Administrator administrator = Administrator.builder()
                .roll(roll)
                .phone(administratorCreateDto.getPhone())
                .userName(administratorCreateDto.getName())
                .address(administratorCreateDto.getAddress())
                .email(administratorCreateDto.getEmail())
                .loginId(administratorCreateDto.getLoginId())
                .password(administratorCreateDto.getPassword())
                .build();
        administratorRepository.save(administrator);
    }
}
