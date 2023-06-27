package com.demo.unit.controller;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.user.UserEntity;
import com.demo.unit.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final IEmailGateway emailGateway;

    public void renameUser(long userId, String userName) {
        UserEntity user = service.findById(userId);
        user.setName(userName);
        service.save(user);
    }

    public boolean greetUser(String email) {
        return emailGateway.sendGreetingEmail(email);
    }
}