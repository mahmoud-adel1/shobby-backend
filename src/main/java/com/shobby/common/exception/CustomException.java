package com.shobby.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final int errorCode;

    public CustomException(int errorCode, String message) {
        this.errorCode = errorCode;
        super(message);
    }

}
