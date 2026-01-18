package com.shobby.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    public String username;
    public String email;
    public String phoneNumber;
}
