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
    CATEGORY_ALREADY_EXISTS("CATEGORY_ALREADY_EXISTS", HttpStatus.CONFLICT),
    CATEGORY_ALREADY_DISABLED("CATEGORY_ALREADY_DISABLED", HttpStatus.CONFLICT),


    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND),
    ENABLED_PRODUCT_NOT_FOUND("ENABLED_PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND),
    PRODUCT_SKU_ALREADY_EXISTS("PRODUCT_SKU_ALREADY_EXISTS", HttpStatus.CONFLICT),
    SELLING_PRICE_LOWER_THAN_COST_PRICE("SELLING_PRICE_LOWER_THAN_COST_PRICE", HttpStatus.BAD_REQUEST),


    ;
    private final String code;
    private final HttpStatus httpStatus;

    ErrorCode(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
