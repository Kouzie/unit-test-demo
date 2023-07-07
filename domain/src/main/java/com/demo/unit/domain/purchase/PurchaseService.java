package com.demo.unit.domain.purchase;

import com.demo.unit.domain.store.Store;
import com.demo.unit.domain.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {
    private final StoreRepository storeRepository;

    protected PurchaseService() {
        storeRepository = null;
    }

    public PurchaseService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Store purchase(long storeId, long productId, int quantity) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        if (!store.hasEnoughInventory(productId, quantity)) {
            throw new IllegalStateException("over quantity");
        }
        store.removeInventory(productId, quantity);
        return storeRepository.save(store);
    }

    public Store findById(long storeId) {
        return storeRepository.findById(storeId).orElseThrow();
    }
}
