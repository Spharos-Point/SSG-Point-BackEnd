package com.spharos.pointapp.store.presentation;

import com.spharos.pointapp.store.application.StoreService;
import com.spharos.pointapp.store.dto.StoreAddDto;
import com.spharos.pointapp.store.dto.StoreGetDto;
import com.spharos.pointapp.store.vo.StoreAddInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/store")
    public void addStore(@RequestBody StoreAddDto storeAdd) {
        ModelMapper modelMapper = new ModelMapper();
        StoreAddDto storeAddDto = modelMapper.map(storeAdd, StoreAddDto.class);
        storeService.addStore(storeAddDto);
    }

    @GetMapping("/store/parnet")
    public List<StoreAddInput> getStoreByNotParnetId() {
        List<StoreGetDto> storeGetDtos = storeService.getStoreByNotParentId();
        List<StoreAddInput> storeGets = new ArrayList<>();
        storeGetDtos.forEach(
                storeGetDto -> {
                    log.info("{}", storeGetDto);
                    storeGets.add(StoreAddInput.builder()
                            .storeName(storeGetDto.getStoreName())
                            .build());
                }
        );
        return storeGets;
    }

    @GetMapping("/store/{parentId}")
    public List<StoreAddInput> getStoreByNotParentId(@PathVariable("parentId") Integer parentId) {
        List<StoreGetDto> storeGetDtos = storeService.getStoreListByParentId(parentId);
        List<StoreAddInput> storeGets = new ArrayList<>();
        storeGetDtos.forEach(
                storeGetDto -> {
                    log.info("{}", storeGetDto);
                    storeGets.add(StoreAddInput.builder()
                                    .storeName(storeGetDto.getStoreName())
                            .build());
                }
        );
    return storeGets;
    }
}
