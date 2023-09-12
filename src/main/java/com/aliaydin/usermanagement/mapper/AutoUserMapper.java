package com.aliaydin.usermanagement.mapper;

import com.aliaydin.usermanagement.dto.UserDto;
import com.aliaydin.usermanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
