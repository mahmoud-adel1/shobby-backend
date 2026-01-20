package com.shobby.security.jwt;

import com.shobby.common.exception.CustomException;

public class InvalidRefreshTokenException extends CustomException {
    public InvalidRefreshTokenException(String message) {
        super(800, message);
    }
}
