package com.demo.unit.domain.user;

import com.demo.unit.domain.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public UserEntity findById(long userId) {
        return repository.findById(userId).orElseThrow();
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Transactional
    public void changeEmail(long userId, String newEmail) {
        UserEntity user = repository.findById(userId).orElseThrow();
        String emailDomain = newEmail.split("@")[1];
        if (user.getEmail().equals(newEmail)) return;
        user.changeEmail(newEmail);
    }
}
