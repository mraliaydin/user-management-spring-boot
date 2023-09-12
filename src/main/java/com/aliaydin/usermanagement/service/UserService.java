package com.aliaydin.usermanagement.service;

import com.aliaydin.usermanagement.dto.UserDto;
import com.aliaydin.usermanagement.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
