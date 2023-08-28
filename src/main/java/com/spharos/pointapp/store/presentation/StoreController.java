package com.spharos.pointapp.store.presentation;


import com.spharos.pointapp.store.application.StoreService;
import com.spharos.pointapp.store.dto.StoreCreateDto;
import com.spharos.pointapp.store.vo.StoreCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;

//    가맹점 생성
    @PostMapping("/store")
    public void createStore(@RequestBody StoreCreate storeCreate) {
        log.info("{}", storeCreate);
        ModelMapper mapper = new ModelMapper();
        StoreCreateDto storeCreateDto = mapper.map(storeCreate, StoreCreateDto.class);
        storeService.createStore(storeCreateDto);
    }
}
