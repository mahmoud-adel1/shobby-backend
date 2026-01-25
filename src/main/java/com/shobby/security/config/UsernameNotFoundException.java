package com.shobby.security.config;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class UsernameNotFoundException extends GeneralException {
    public UsernameNotFoundException() {
        super(ErrorCode.USERNAME_NOT_FOUND);
    }
}
