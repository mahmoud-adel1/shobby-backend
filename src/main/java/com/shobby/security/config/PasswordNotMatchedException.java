package com.shobby.security.config;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class PasswordNotMatchedException extends GeneralException {
    public PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}
