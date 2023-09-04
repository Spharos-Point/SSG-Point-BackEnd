package com.spharos.pointapp.store.infrastructure;

import com.spharos.pointapp.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>{
}
