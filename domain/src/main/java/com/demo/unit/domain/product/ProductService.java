package com.demo.unit.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public ProductEntity findById(long productId) {
        return repository.findById(productId).orElseThrow();
    }
}
