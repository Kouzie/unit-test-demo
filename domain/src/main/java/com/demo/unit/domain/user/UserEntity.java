package com.demo.unit.domain.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;

    protected UserEntity() {

    }

    public void setName(String name) {
        this.name = normalizeName(name);
    }

    private String normalizeName(String name) {
        String result = name == null ? "" : name.trim();
        if (result.length() > 50)
            return result.substring(0, 50);
        return result;
    }
}
