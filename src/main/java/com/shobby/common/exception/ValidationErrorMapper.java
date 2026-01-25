package com.shobby.common.exception;

import org.springframework.validation.FieldError;

public class ValidationErrorMapper {
    public static ValidationError toValidationError(FieldError fieldError) {
        return ValidationError
                .builder()
                .field(fieldError.getField())
                .errorCode(fieldError.getDefaultMessage())
                .build();
    }
}
