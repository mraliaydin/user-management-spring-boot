package com.aliaydin.usermanagement.service.impl;

import com.aliaydin.usermanagement.dto.UserDto;
import com.aliaydin.usermanagement.entity.User;
import com.aliaydin.usermanagement.exception.EmailAlreadyExistsException;
import com.aliaydin.usermanagement.exception.ResourceNotFoundException;
import com.aliaydin.usermanagement.mapper.AutoUserMapper;
import com.aliaydin.usermanagement.mapper.UserMapper;
import com.aliaydin.usermanagement.repository.UserRepository;
import com.aliaydin.usermanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto into User JPA
        // User user = UserMapper.mapToUser(userDto);

        //User user = modelMapper.map(userDto,User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for a User");
        }


        User user = AutoUserMapper.MAPPER.mapToUser(userDto);


        User savedUser =  userRepository.save(user);

        // Convert User JPA entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        //UserDto savedUserDto = modelMapper.map(user, UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(user);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", userId)
                );

        //UserDto findUser = UserMapper.mapToUserDto(optionalUser.get());
        UserDto findUser = modelMapper.map(user, UserDto.class);


        return findUser;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        /*
        return users.stream().map(UserMapper::mapToUserDto)
            .collect(Collectors.toList());
         */

        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );


        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existUser);

        //UserDto userDto = UserMapper.mapToUserDto(updatedUser);
        UserDto userDto = modelMapper.map(updatedUser, UserDto.class);

        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User existUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
