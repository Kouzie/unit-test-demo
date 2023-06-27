package com.demo.unit.adaptor.impl;

import com.demo.unit.adaptor.IEmailGateway;
import org.springframework.stereotype.Component;

@Component
public class EmailGateway implements IEmailGateway{
    @Override
    public boolean sendGreetingEmail(String email) {
        return false;
    }
}