package com.demo.unit.domain.delivery;

import java.time.LocalDateTime;

public class DeliveryService {
    public boolean isDeliveryValid(Delivery delivery) {
        return delivery.getDate().isAfter(LocalDateTime.now().plusHours(24 + 23));
    }
}