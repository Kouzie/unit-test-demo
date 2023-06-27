package com.demo.unit;

import com.demo.unit.domain.product.ProductEntity;
import com.demo.unit.domain.store.StoreEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class ParameterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "5,5",
            "10,0"
    })
    void purchase_failed_when_not_enough_inventory(int quantity, int expectedQuantity) {
        // 준비
        ProductEntity shampoo = new ProductEntity("shampoo");
        long productId = 1l;
        StoreEntity sut = createStoreWithInventory(productId, 10);

        // 실행
        sut.removeInventory(productId, quantity);

        // 검증
        Assertions.assertEquals(expectedQuantity, sut.getInventory(productId));
    }

    private StoreEntity createStoreWithInventory(long productId, int quantity) {
        StoreEntity store = new StoreEntity("sample store");
        store.addInventory(productId, quantity);
        return store;
    }

    public static class DeliveryArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(LocalDateTime.now().plusDays(-1), false),
                    Arguments.of(LocalDateTime.now(), false),
                    Arguments.of(LocalDateTime.now().plusDays(1), false),
                    Arguments.of(LocalDateTime.now().plusDays(2), true)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(DeliveryArguments.class)
    void can_detect_an_invalid_delivery_date(LocalDateTime deliveryDate, boolean expected) {
        DeliveryService sut = new DeliveryService();
        Delivery delivery = new Delivery(deliveryDate);
        boolean isValid = sut.isDeliveryValid(delivery); // delivery 가 2일 후이면 true
        Assertions.assertEquals(expected, isValid);

    }
}