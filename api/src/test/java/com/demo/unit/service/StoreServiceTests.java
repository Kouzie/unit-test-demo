package com.demo.unit.service;

import com.demo.unit.domain.store.Store;
import com.demo.unit.domain.store.StoreRepository;
import com.demo.unit.domain.store.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTests {

    @Mock
    StoreRepository repository;

    @InjectMocks
    StoreService service;

    @Test
    void save_and_find_store() {
        Store store1 = createStore("test store1");
        Store store2 = createStore("test store2");
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(store1);
        Mockito.when(repository.findById(2l)).thenReturn(Optional.of(store2));
        Store sut1 = service.save(store1);
        Store sut2 = service.findById(2l);
        Assertions.assertEquals("test store1", sut1.getName());
        Assertions.assertEquals("test store2", sut2.getName());
    }

    private Store createStore(String storeName) {
        Store store = new Store(storeName);
        store.addInventory(1l, 10);
        store.addInventory(2l, 10);
        return store;
    }
}
