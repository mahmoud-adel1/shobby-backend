package com.shobby.auth.exception;

import com.shobby.common.exception.CustomException;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException(String message) {
        super(500, message);
    }
}
