package com.demo.unit.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    CUSTOMER(1),
    EMPLOYEE(2)
    ;

    private final int key;
}
