package com.demo.unit.ui.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GreetEmailRequestDto {
    @NotBlank
    private String email;
}
