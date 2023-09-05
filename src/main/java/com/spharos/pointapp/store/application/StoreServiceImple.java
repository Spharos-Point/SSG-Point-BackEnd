package com.spharos.pointapp.store.application;

import com.spharos.pointapp.store.domain.Store;
import com.spharos.pointapp.store.dto.StoreAddDto;
import com.spharos.pointapp.store.dto.StoreGetDto;
import com.spharos.pointapp.store.infrastructure.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreServiceImple implements StoreService{

    private final StoreRepository storeRepository;

    @Override
    public void addStore(StoreAddDto storeAddDto) {
        storeRepository.save(Store.builder()
                        .storeName(storeAddDto.getStoreName())
                        .logoImage(storeAddDto.getLogoImage())
                .build());
    }

    @Override
    public StoreGetDto getStoreById(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        StoreGetDto storeGetDto = modelMapper.map(storeRepository.findById(id).get(), StoreGetDto.class);
        return storeGetDto;
    }

    @Override
    public List<StoreGetDto> getStoreList() {
        ModelMapper modelMapper = new ModelMapper();
        List<StoreGetDto> storeGetDtoList = storeRepository.findAll().stream()
                .map(store -> modelMapper.map(store, StoreGetDto.class))
                .toList();
        return storeGetDtoList;
    }

    @Override
    public List<StoreGetDto> getStoreByNotParentId() {
        return null;
    }

}
