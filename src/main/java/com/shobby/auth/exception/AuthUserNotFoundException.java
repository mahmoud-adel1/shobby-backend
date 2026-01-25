package com.shobby.auth.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;
import lombok.Getter;

@Getter
public class AuthUserNotFoundException extends GeneralException {
    public AuthUserNotFoundException(String message) {
        super(ErrorCode.AUTH_USER_NOT_FOUND);
    }
}
