package com.demo.unit.service;

import com.demo.unit.domain.purchase.PurchaseService;
import com.demo.unit.domain.store.Store;
import com.demo.unit.domain.store.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

// mock test
public class PurchaseServiceTests {

    @Test
    void purchase_normal_mock_test() {
        long productId = 23;
        int quantity = 10;
        long storeId = 1;
        Store store = createStore(productId, quantity);
         StoreRepository storeRepository = Mockito.mock(StoreRepository.class);
        PurchaseService service = new PurchaseService(storeRepository);
        Mockito.when(storeRepository.findById(storeId))
                .thenReturn(Optional.of(store));
        Mockito.when(storeRepository.save(ArgumentMatchers.any(Store.class)))
                .thenReturn(store);
        Store sut = service.purchase(storeId, productId, 3);
        Assertions.assertEquals(quantity - 3, sut.getInventory(productId));
        Mockito.verify(storeRepository, Mockito.times(1)).findById(storeId);
        Mockito.verify(storeRepository, Mockito.times(2)).save(ArgumentMatchers.any(Store.class));
    }

    private Store createStore(long productId, int quantity) {
        Store store = new Store("mock-store");
        store.addInventory(productId, quantity);
        return store;
    }

    @Test
    void purchase_abnormal_test() {
        long productId = 23;
        int quantity = 10;
        long storeId = 1;
        Store store = createStore(productId, quantity);
        StoreRepository storeRepository = Mockito.mock(StoreRepository.class);
        PurchaseService service = new PurchaseService(storeRepository);
        Mockito.when(storeRepository.findById(storeId))
                .thenReturn(Optional.of(store));
        Mockito.when(storeRepository.save(ArgumentMatchers.any(Store.class)))
                .thenReturn(store);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            service.purchase(storeId, productId, 11);
        });
    }


}
