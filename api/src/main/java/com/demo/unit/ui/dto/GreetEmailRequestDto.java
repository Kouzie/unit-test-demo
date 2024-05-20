package com.demo.unit.ui.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class GreetEmailRequestDto {
    @NotBlank
    private String email;
}
