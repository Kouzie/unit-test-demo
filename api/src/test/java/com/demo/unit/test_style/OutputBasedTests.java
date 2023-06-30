package com.demo.unit.test_style;

import com.demo.unit.domain.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutputBasedTests {
    public static class PriceEngine {
        public double calculatingDiscount(Product[] products) {
            double discount = products.length * 0.01;
            return Math.min(discount, 0.2);
        }
    }

    @Test
    void discount_of_two_product() {
        Product p1 = new Product("shampoo");
        Product p2 = new Product("book");
        PriceEngine sut = new PriceEngine();

        double discount = sut.calculatingDiscount(new Product[]{p1, p2});
        Assertions.assertEquals(0.02, discount);
    }
}
