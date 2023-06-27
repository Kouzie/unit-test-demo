package com.demo.unit.test_style;

import com.demo.unit.domain.product.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutputBasedTests {

    public static class PriceEngine {
        public double calculatingDiscount(ProductEntity[] products) {
            double discount = products.length * 0.01;
            return Math.min(discount, 0.2);
        }
    }

    @Test
    void discount_of_two_product() {
        ProductEntity p1 = new ProductEntity("shampoo");
        ProductEntity p2 = new ProductEntity("book");
        PriceEngine sut = new PriceEngine();

        double discount = sut.calculatingDiscount(new ProductEntity[]{p1, p2});
        Assertions.assertEquals(0.02, discount);
    }
}
