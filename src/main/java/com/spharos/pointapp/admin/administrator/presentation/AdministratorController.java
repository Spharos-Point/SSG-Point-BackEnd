package com.spharos.pointapp.admin.administrator.presentation;

import com.spharos.pointapp.admin.administrator.application.AdministratorServiceImple;
import com.spharos.pointapp.admin.administrator.dto.AdministratorCreateDto;
import com.spharos.pointapp.admin.administrator.vo.InAdministrator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorServiceImple administratorServiceImple;

    @Operation(summary = "어드민 계정 생성", description = "어드민 게정을 생성합니다.", tags = { "Administrator Controller" })
    @PostMapping("/administrator")
    public ResponseEntity<String> addAdmin(
            @RequestBody InAdministrator inAdministrator
            ){
        AdministratorCreateDto administratorCreateDto = AdministratorCreateDto.builder()
                .roll(inAdministrator.getRoll())
                .phone(inAdministrator.getPhone())
                .address(inAdministrator.getAddress())
                .loginId(inAdministrator.getLoginId())
                .email(inAdministrator.getEmail())
                .name(inAdministrator.getName())
                .password(inAdministrator.getPassword())
                .build();
        administratorServiceImple.createAdministrator(administratorCreateDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
