package com.shobby.auth.dto.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RefreshResult {
    private String accessToken;
    private String refreshToken;
}
