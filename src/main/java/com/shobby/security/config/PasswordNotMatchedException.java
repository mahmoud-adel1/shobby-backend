package com.shobby.security.config;

import com.shobby.common.exception.CustomException;

public class PasswordNotMatchedException extends CustomException {
    public PasswordNotMatchedException(String message) {
        super(720, message);
    }
}
