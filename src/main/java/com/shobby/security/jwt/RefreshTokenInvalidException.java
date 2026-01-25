package com.shobby.security.jwt;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class RefreshTokenInvalidException extends GeneralException {
    public RefreshTokenInvalidException() {
        super(ErrorCode.REFRESH_TOKEN_INVALID);
    }
}
