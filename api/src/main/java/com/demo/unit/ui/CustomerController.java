package com.demo.unit.ui;

import com.demo.unit.domain.purchase.PurchaseService;
import com.demo.unit.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final PurchaseService purchaseService;

    public Store purchase(long storeId, int productId, int quantity) {
        return purchaseService.purchase(storeId, productId, quantity);
    }
}
