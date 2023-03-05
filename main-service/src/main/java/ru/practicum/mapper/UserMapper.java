package ru.practicum.mapper;

import ru.practicum.dto.user.NewUserDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.dto.user.UserShortDto;
import ru.practicum.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(NewUserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static Collection<UserResponseDto> toUserResponseDtoCollection(Collection<User> users) {
        return users.stream()
                .map(UserMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }
}
