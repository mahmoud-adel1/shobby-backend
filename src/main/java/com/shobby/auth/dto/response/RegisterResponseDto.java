package com.shobby.auth.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {
    private String username;
    private String message;
    private String accessToken;
}
