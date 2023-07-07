package com.demo.unit.ui;

import com.demo.unit.domain.purchase.PurchaseService;
import com.demo.unit.domain.store.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {

    @Spy
    PurchaseService service;

    @Test
    public void purchase() {
        int productId = 23;
        int quantity = 7;
        Mockito.doAnswer(invocation -> {
            Store store = new Store("spy-store");
            store.addInventory(productId, 20 - quantity);
            return store;
        }).when(service).purchase(0, productId, quantity);
        CustomerController controller = new CustomerController(service);


        Store sut = controller.purchase(0, productId, quantity);

        Assertions.assertEquals("spy-store", sut.getName());
        Assertions.assertEquals(13, sut.getInventory(productId));
    }
}
