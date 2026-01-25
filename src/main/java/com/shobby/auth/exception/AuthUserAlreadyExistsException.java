package com.shobby.auth.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class AuthUserAlreadyExistsException extends GeneralException {
    public AuthUserAlreadyExistsException() {
        super(ErrorCode.AUTH_USER_ALREADY_EXISTS);
    }
}
