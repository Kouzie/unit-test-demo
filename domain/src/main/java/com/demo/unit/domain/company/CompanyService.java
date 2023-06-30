package com.demo.unit.domain.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;

    @Transactional(readOnly = true)
    public Company findById(String domain) {
        return repository.findById(domain).orElseThrow();
    }

    @Transactional
    public Company save(Company user) {
        return repository.save(user);
    }
}
