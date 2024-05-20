package com.demo.unit.ui.dto;

import com.demo.unit.domain.user.UserEntity;
import com.demo.unit.domain.user.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long userId;
    private String email;
    private UserType type;
    private String name;

    protected UserDto() {
    }

    public UserDto(UserEntity user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.type = user.getType();
        this.name = user.getName();
    }
}
