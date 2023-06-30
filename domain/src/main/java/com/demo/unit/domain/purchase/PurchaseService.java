package com.demo.unit.domain.purchase;

import com.demo.unit.domain.store.Store;
import com.demo.unit.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final StoreRepository storeRepository;

    @Transactional
    public boolean purchase(long storeId, long productId, int quantity) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        if (!store.hasEnoughInventory(productId, quantity)) {
            return false;
        }
        store.removeInventory(productId, quantity);
        return false;
    }
}
