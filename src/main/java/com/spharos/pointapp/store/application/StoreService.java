package com.spharos.pointapp.store.application;

import com.spharos.pointapp.store.dto.StoreCreateDto;

public interface StoreService {
    void createStore(StoreCreateDto storeCreateDto);
}
