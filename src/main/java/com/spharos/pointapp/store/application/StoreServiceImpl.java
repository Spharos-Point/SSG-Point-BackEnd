package com.spharos.pointapp.store.application;

import com.spharos.pointapp.store.domain.Store;
import com.spharos.pointapp.store.domain.StoreList;
import com.spharos.pointapp.store.domain.StoreListConverter;
import com.spharos.pointapp.store.dto.StoreCreateDto;
import com.spharos.pointapp.store.infrastructure.StoreRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;

    @Override
    @Convert(converter = StoreListConverter.class)
    public void createStore(StoreCreateDto storeCreateDto) {
        StoreList storeList = new StoreListConverter().convertToEntityAttribute(storeCreateDto.getStoreList());
        storeRepository.save(Store.builder()
                .partnerName(storeList)
                .build());
    }
}
