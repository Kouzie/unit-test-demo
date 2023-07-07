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

    /*@JsonCreator
    public UserType fromJson(String val) {
        for (UserType value : UserType.values()) {
            if (val.equals(value.name())) return value;
        }
        return null;
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }*/
}
