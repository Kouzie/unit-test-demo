package com.demo.unit;

import java.time.LocalDateTime;

public class DeliveryService {
    public boolean isDeliveryValid(Delivery delivery) {
        return delivery.getDate().isAfter(LocalDateTime.now().plusHours(24 + 23));
    }
}