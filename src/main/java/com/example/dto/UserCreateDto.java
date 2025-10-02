package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserCreateDto {

    @Schema(description = "Имя пользователя", example = "Vasya")
    private String name;

    @Schema(description = "Email пользователя", example = "vasya@example.ru")
    private String email;

    @Schema(description = "Возраст пользователя", example = "30")
    private Integer age;
}
