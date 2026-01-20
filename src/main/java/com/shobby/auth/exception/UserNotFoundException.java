package com.shobby.auth.exception;

import com.shobby.common.exception.CustomException;
import lombok.Getter;

@Getter
public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(520, message);
    }
}
