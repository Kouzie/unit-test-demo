package com.demo.unit.adaptor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestUserPost {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
