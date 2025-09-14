package com.example.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    private String email;
    private Integer age;
}
