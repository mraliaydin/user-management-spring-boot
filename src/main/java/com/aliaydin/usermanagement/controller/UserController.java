package com.aliaydin.usermanagement.controller;

import com.aliaydin.usermanagement.dto.UserDto;
import com.aliaydin.usermanagement.entity.User;
import com.aliaydin.usermanagement.exception.ErrorDetails;
import com.aliaydin.usermanagement.exception.ResourceNotFoundException;
import com.aliaydin.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // build create User REST API
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto saved = userService.createUser(userDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto find = userService.getUserById(id);

        return ResponseEntity.ok(find);
    }

    // Build get all users REST API
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> list = userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    // Build update user REST API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Long id, @RequestBody UserDto user){
        user.setId(id);
        UserDto updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }


}
