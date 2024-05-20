package com.demo.unit.domain.customer;

import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    protected Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }
}
