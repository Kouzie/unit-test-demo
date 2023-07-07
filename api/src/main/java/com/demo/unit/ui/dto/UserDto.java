package com.demo.unit.ui.dto;

import com.demo.unit.domain.user.User;
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

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.type = user.getType();
        this.name = user.getName();
    }
}
