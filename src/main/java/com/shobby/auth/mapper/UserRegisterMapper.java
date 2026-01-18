package com.shobby.auth.mapper;

import com.shobby.auth.dto.request.RegisterRequestDto;
import com.shobby.auth.dto.response.RegisterResponseDto;
import com.shobby.role.entity.Role;
import com.shobby.role.enums.RoleType;
import com.shobby.user.entity.User;

public class UserRegisterMapper {

    public static User mapToUser(RegisterRequestDto request) {
        return User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .mobileNumber(request.getMobileNumber())
                .build();

    }

}
