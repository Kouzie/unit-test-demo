package com.demo.unit.adaptor.impl;

import com.demo.unit.adaptor.IEmailGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailGateway implements IEmailGateway{

    private final SMTPClient smtpClient;

    @Override
    public boolean sendGreetingEmail(String email) {
        return smtpClient.sendEmail(email, "hello word");
    }
}