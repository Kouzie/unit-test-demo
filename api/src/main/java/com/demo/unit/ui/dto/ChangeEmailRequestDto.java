package com.demo.unit.ui.dto;


import lombok.Data;

@Data
public class ChangeEmailRequestDto {
    private final Long userId;
    private final String mail;

}
