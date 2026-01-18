package com.shobby.user.mapper;

import com.shobby.user.dto.UserResponseDto;
import com.shobby.user.entity.User;

public class UserMapper {

    public static UserResponseDto mapToDto(User user) {
        return UserResponseDto
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getMobileNumber())
                .build();
    }
}
