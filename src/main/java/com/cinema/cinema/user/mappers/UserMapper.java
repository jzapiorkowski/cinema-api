package com.cinema.cinema.user.mappers;

import com.cinema.cinema.user.dto.UserOutputDto;
import com.cinema.cinema.user.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutputDto userToUserOutputDto(User user);
}
