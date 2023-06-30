package com.demo.unit.test_style;

import com.demo.unit.domain.product.Product;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StateBasedTests {
    @Getter
    public static class Order {
        private List<Product> products = new ArrayList<>();

        public void addProduct(Product productEnum) {
            products.add(productEnum);
        }
    }

    @Test
    void adding_a_product_to_an_order() {
        Product product = new Product("Hand wash");
        Order sut = new Order();

        sut.addProduct(product);

        Assertions.assertEquals(1, sut.getProducts().size());
        Assertions.assertEquals(product, sut.getProducts().get(0));
    }
}
