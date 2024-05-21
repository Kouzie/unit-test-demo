package com.demo.unit.domain.delivery;

import java.time.LocalDateTime;

public class Delivery {
    private LocalDateTime date;

    public Delivery(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}