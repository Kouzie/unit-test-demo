package com.demo.unit.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerEntity findById(long customerId) {
        return repository.findById(customerId).orElseThrow();
    }
}
