package com.shobby.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    AUTH_USER_NOT_FOUND("AUTH_USER_NOT_FOUND", HttpStatus.NOT_FOUND),
    AUTH_USER_ALREADY_EXISTS("AUTH_USER_ALREADY_EXISTS", HttpStatus.CONFLICT),

    REFRESH_TOKEN_INVALID("REFRESH_TOKEN_INVALID", HttpStatus.UNAUTHORIZED),

    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCH("PASSWORD_NOT_MATCH", HttpStatus.UNAUTHORIZED),


    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS("CATEGORY_ALREADY_EXISTS", HttpStatus.CONFLICT);

    private final String code;
    private final HttpStatus httpStatus;

    ErrorCode(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
