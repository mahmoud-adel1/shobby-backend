package com.shobby.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class GeneralException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }
}
