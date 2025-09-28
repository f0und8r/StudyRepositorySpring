package com.example.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}