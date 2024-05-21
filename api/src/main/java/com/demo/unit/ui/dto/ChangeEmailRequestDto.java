package com.demo.unit.ui.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangeEmailRequestDto {
    private Long userId;
    private String mail;

    protected ChangeEmailRequestDto() {
    }
}
