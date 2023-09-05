package com.spharos.pointapp.store.application;

import com.spharos.pointapp.store.dto.StoreAddDto;
import com.spharos.pointapp.store.dto.StoreGetDto;

import java.util.List;

public interface StoreService {

    void addStore(StoreAddDto storeAddDto);
    StoreGetDto getStoreById(Integer id);
    List<StoreGetDto> getStoreList();
    List<StoreGetDto> getStoreByNotParentId();
    List<StoreGetDto> getStoreListByParentId(Integer parentId);
}
