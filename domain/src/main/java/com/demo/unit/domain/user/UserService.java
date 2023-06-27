package com.demo.unit.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional(readOnly = true)
    public UserEntity findById(long userId) {
        return repository.findById(userId).orElseThrow();
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }
}
