package com.example.service;

import com.example.controller.UserController;
import com.example.dto.UserCreateDto;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto createUser(UserCreateDto dto) {
        User user = new User(dto.getName(), dto.getEmail(), dto.getAge());
        userRepository.save(user);
        return mapToDto(user);
    }

    public UserDto getUser(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id, UserCreateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        userRepository.save(user);
        return mapToDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());

        dto.add(linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel());
        dto.add(linkTo(methodOn(UserController.class).getAll()).withRel("all-users"));
        dto.add(linkTo(methodOn(UserController.class).update(user.getId(),null)).withRel("update"));
        dto.add(linkTo(methodOn(UserController.class).delete(user.getId())).withRel("delete"));

        return dto;
    }
}
