package com.demo.unit.domain.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository repository;

    public StoreEntity findById(long storeId) {
        return repository.findById(storeId).orElseThrow();
    }
}
